package com.geekhome.common.utils;

import java.awt.Image;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;

/**
 * @author Pinky Lam 908716835@qq.com
 * @date 2017年7月19日 下午3:53:16
 */
public class FileUtil {

	/**
	 * @Title: getUplodFilePath
	 * @Description: 获取项目所在路径
	 * @return String
	 */
	public static String getUplodFilePath() {
		String path = FileUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		path = path.substring(1, path.length());
		try {
			path = java.net.URLDecoder.decode(path, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int lastIndex = path.lastIndexOf("/") + 1;
		path = path.substring(0, lastIndex);
		File file = new File("");
		return file.getAbsolutePath() + "/";
	}

	/**
	 * 判断文件是否是图片类型
	 *
	 * @param imageFile
	 * @return
	 */
	public static boolean isImage(InputStream imageFile) {
		try {
			Image img = ImageIO.read(imageFile);
			if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
