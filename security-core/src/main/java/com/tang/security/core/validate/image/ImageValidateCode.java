/**
 * 
 */
package com.tang.security.core.validate.image;

import com.tang.security.core.validate.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @Description 验证码对象
 * @Author tang
 * @Date 2019-08-24 20:17
 * @Version 1.0
 **/
public class ImageValidateCode extends ValidateCode {

	/**
	 * 存储生成的image
	 */
	private BufferedImage image; 
	
	public ImageValidateCode(BufferedImage image, String code, int expireIn){
		super(code, expireIn);
		this.image = image;
	}
	
	public ImageValidateCode(BufferedImage image, String code, LocalDateTime expireTime){
		super(code, expireTime);
		this.image = image;
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

}
