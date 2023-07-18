package com.deliveryservice.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import com.deliveryservice.entity.MenuImg;
import com.deliveryservice.repository.MenuImgRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MenuImgService {

	private String menuImgLocation = "C:/delivery/menu";
	private final MenuImgRepository menuImgRepository;
	private final FileService fileService;
	
	public void saveMenuImg(MenuImg menuImg,MultipartFile menuImgFile) throws Exception{
		String menuOriImgName = menuImgFile.getOriginalFilename();
		String menuimgName = "";
		String menuimgUrl = "";
		
		if(!StringUtils.isEmpty(menuOriImgName)) {
			menuimgName = fileService.uploadFile(menuImgLocation,
					menuOriImgName, menuImgFile.getBytes());
			menuimgUrl = "/images/menu/" + menuimgName;
			
		}
		
		menuImg.updateMenuImg(menuOriImgName, menuimgName, menuimgUrl);
		menuImgRepository.save(menuImg);
	}

	public void updateMenuImg(Long menuImgId, MultipartFile menuImgFile) throws Exception{
		if(!menuImgFile.isEmpty()) {
			MenuImg savedMenuImg = menuImgRepository.findById(menuImgId)
									.orElseThrow(EntityNotFoundException::new);
			
			if(!StringUtils.isEmpty(savedMenuImg.getMenuImgName())) {
				fileService.deleteFile(menuImgLocation + "/" + savedMenuImg.getMenuImgName());
			}
			
			String menuOriImgName = menuImgFile.getOriginalFilename();
			String menuImgName = fileService.uploadFile(menuImgLocation, menuOriImgName, menuImgFile.getBytes());
			String menuImgUrl = "/images/menu/" + menuImgName;
			
			savedMenuImg.updateMenuImg(menuOriImgName, menuImgName, menuImgUrl);
		} 
	}
	
	
}
