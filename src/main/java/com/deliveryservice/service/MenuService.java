package com.deliveryservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.deliveryservice.dto.MenuDto;
import com.deliveryservice.dto.MenuFormDto;
import com.deliveryservice.dto.MenuImgDto;
import com.deliveryservice.dto.MenuSearchDto;
import com.deliveryservice.entity.Menu;
import com.deliveryservice.entity.MenuImg;
import com.deliveryservice.repository.MenuImgRepository;
import com.deliveryservice.repository.MenuRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MenuService {
	private final MenuRepository menuRepository;
	private final MenuImgService menuImgService;
	private final MenuImgRepository menuImgRepository;
	
	public Long saveMenu(MenuFormDto menuFormDto, List<MultipartFile> menuImgFileList) throws Exception{
		
		Menu menu = menuFormDto.createMenu();
		menuRepository.save(menu);
		
		for(int i = 0; i<menuImgFileList.size(); i++) {
			MenuImg menuImg = new MenuImg();
			menuImg.setMenu(menu);
			
			if( i == 0 ) {
				menuImg.setRepimgYn("Y");
			} else{
				menuImg.setRepimgYn("N");
			}
			
			menuImgService.saveMenuImg(menuImg, menuImgFileList.get(i));
			}
		return menu.getId();
		}
		
	@Transactional(readOnly = true)
	public MenuFormDto getMenuDtl(Long menuId) {
		List<MenuImg> menuImgList = menuImgRepository.findByMenuIdOrderByIdAsc(menuId);
		
		List<MenuImgDto> menuImgDtoList = new ArrayList<>();
		for(MenuImg menuImg : menuImgList) {
			MenuImgDto menuImgDto = MenuImgDto.of(menuImg);
			menuImgDtoList.add(menuImgDto);
		}
		
		Menu menu = menuRepository.findById(menuId)
									.orElseThrow(EntityNotFoundException::new);
		
		MenuFormDto menuFormDto = MenuFormDto.of(menu);
		
		menuFormDto.setMenuImgDtoList(menuImgDtoList);
		
		return menuFormDto;
	}
	
	public Long updateMenu(MenuFormDto menuFormDto, List<MultipartFile> menuImgFileList) throws Exception{
		Menu menu = menuRepository.findById(menuFormDto.getId())
								.orElseThrow(EntityNotFoundException::new);
		menu.updateMenu(menuFormDto);
		
		List<Long> menuImgIds = menuFormDto.getMenuImgIds();
		
		for(int i=0; i<menuImgFileList.size(); i++) {
			menuImgService.updateMenuImg(menuImgIds.get(i), menuImgFileList.get(i));
		}
		return menu.getId();
	}
	
	public void deleteMenu(Long menuId) {
		Menu menu = menuRepository.findById(menuId)
					.orElseThrow(EntityNotFoundException::new);
		menuRepository.delete(menu);
	}
	
	@Transactional(readOnly = true)
	public Page<Menu> getAdminMenuPage(MenuSearchDto menuSearchDto, Pageable pageable){
		Page<Menu> menuPage = menuRepository.getAdminMenuPage(menuSearchDto, pageable);
		return menuPage;
	}
	
	@Transactional(readOnly = true)
	public Page<MenuDto> getMenuPage(MenuSearchDto menuSearchDto, Pageable pageable){
		Page<MenuDto> menuPage = menuRepository.getMenuPage(menuSearchDto, pageable);
		return menuPage;
	}
	
	}
