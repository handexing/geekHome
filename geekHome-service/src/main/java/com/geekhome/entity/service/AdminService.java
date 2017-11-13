package com.geekhome.entity.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.geekhome.common.utils.PageUtil;
import com.geekhome.common.utils.PasswordUtil;
import com.geekhome.entity.Admin;
import com.geekhome.entity.AdminRole;
import com.geekhome.entity.dao.AdminDao;
import com.geekhome.entity.dao.AdminRoleDao;

@Service
public class AdminService {

	@Autowired
	private AdminDao adminDao;
	@Autowired
	private AdminRoleDao adminRoleDao;
	@Autowired
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public Page<Admin> getAdminList(Integer page, Integer rows) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT a.ID id ,a.USER_NAME userName,a.`PASSWORD` password,a.STATE state,").append(
				"a.IS_SYSTEM isSystem,a.SALT salt,a.CREATE_TIME createTime,a.UPDATE_TIME updateTime,r.NAME roleName")
				.append(" FROM admin a LEFT JOIN admin_role ar ON a.ID=ar.ADMIN_ID")
				.append(" LEFT JOIN role r ON ar.ROLE_ID=r.ID");
		final int firstRecord = PageUtil.calcPage(page) * rows;
		final List<Admin> list = entityManager.createNativeQuery(sql.toString(), "getAdminList")
				.setFirstResult(firstRecord).setMaxResults(rows).getResultList();
		final int total = adminDao.getAdminListCnt();
		final Page<Admin> pages = new PageImpl<>(list, null, total);
		return pages;
	}

	public Integer saveAdmin(Admin admin) {

		if (admin.getId() != null) {
			List<Admin> list = adminDao.findAdminByIdNotAndUserName(admin.getId(), admin.getUserName());
			if (list.isEmpty()) {
				Admin a = adminDao.findOne(admin.getId());
				if (!StringUtils.isEmpty(admin.getPassword())) {
					String password = PasswordUtil.createAdminPwd(admin.getPassword(), a.getCredentialsSalt());
					admin.setPassword(password);
				} else {
					admin.setPassword(a.getPassword());
				}
				admin.setSalt(a.getSalt());
				admin.setIsSystem(a.getIsSystem());
				admin.setCreateTime(a.getCreateTime());
				admin.setUpdateTime(new Date());
				adminDao.save(admin);
				saveAdminRole(admin);
				return 1;
			} else {
				return -1;// 已存在相同名称
			}
		} else {
			Admin a = adminDao.findAdminByUserName(admin.getUserName());
			if (a == null) {
				String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
				admin.setSalt(salt);
				String password = PasswordUtil.createAdminPwd(admin.getPassword(), admin.getCredentialsSalt());
				admin.setPassword(password);
				admin.setIsSystem(0);
				admin.setCreateTime(new Date());
				adminDao.save(admin);
				saveAdminRole(admin);
				return 1;
			} else {
				return -1;// 已存在相同名称
			}
		}
	}

	private void saveAdminRole(Admin admin) {
		adminRoleDao.deleteAdmin(admin.getId());

		List<Long> roles = admin.getRoles();
		for (Long roleId : roles) {
			AdminRole adminRole = new AdminRole();
			adminRole.setAdminId(admin.getId());
			adminRole.setRoleId(roleId);
			adminRoleDao.save(adminRole);
		}
	}

}
