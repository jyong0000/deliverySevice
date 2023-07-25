package com.deliveryservice.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import com.deliveryservice.constant.MenuStatus;
import com.deliveryservice.dto.MenuDto;
import com.deliveryservice.dto.MenuSearchDto;
import com.deliveryservice.dto.QMenuDto;
import com.deliveryservice.entity.Menu;
import com.deliveryservice.entity.QMenu;
import com.deliveryservice.entity.QMenuImg;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

public class MenuRepositoryCustomImpl implements MenuRepositoryCustom{
	
	private JPAQueryFactory queryFactory;
	
	public MenuRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}
	
	private BooleanExpression searchMenuStatusEq(MenuStatus searchMenuStatus) {
		return searchMenuStatus == null ? null :
			QMenu.menu.menuStatus.eq(searchMenuStatus);
	}
	
	private BooleanExpression searchByLike(String searchBy, String searchQuery) {
		if(StringUtils.equals("menuNm", searchBy)) {
			return QMenu.menu.menuNm.like("%" + searchQuery + "%");
		}else if(StringUtils.equals("createdBy", searchBy)) {
			return QMenu.menu.createdBy.like("%" + searchQuery + "%");
		}
		return null;
	}
	
	@Override
	public Page<Menu> getAdminMenuPage(MenuSearchDto menuSearchDto, Pageable pageable){
		List<Menu> content = queryFactory
							.selectFrom(QMenu.menu)
							.where(	searchMenuStatusEq(menuSearchDto.getSearchmenuStatus()),
									searchByLike(menuSearchDto.getSearchBy(), menuSearchDto.getSearchQuery()))
							.orderBy(QMenu.menu.id.desc())
							.offset(pageable.getOffset())
							.limit(pageable.getPageSize())
							.fetch();
		
		long total = queryFactory.select(Wildcard.count).from(QMenu.menu)
				.where(	searchMenuStatusEq(menuSearchDto.getSearchmenuStatus()),
						searchByLike(menuSearchDto.getSearchBy(), menuSearchDto.getSearchQuery()))
				.fetchOne();
		
		return new PageImpl<>(content, pageable, total);
						
	}

	private BooleanExpression menuNmLike(String searchQuery) {
		return StringUtils.isEmpty(searchQuery) ?
				null : QMenu.menu.menuNm.like("%" + searchQuery + "%");
	}
	
	@Override
	public Page<MenuDto> getMenuPage(MenuSearchDto menuSearchDto, Pageable pageable) {
		QMenu menu = QMenu.menu;
		QMenuImg menuImg = QMenuImg.menuImg;
		
		List<MenuDto> content = queryFactory
				.select(
						new QMenuDto(
								menu.id,
								menu.menuNm,
								menu.menuDetail,
								menuImg.menuImgUrl,
								menu.price
								)
						)
				.from(menuImg)
				.join(menuImg.menu, menu)
				.where(menuNmLike(menuSearchDto.getSearchQuery()))
				.orderBy(menu.id.desc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
		
		long total = queryFactory
				.select(Wildcard.count)
				.from(menuImg)
				.join(menuImg.menu, menu)
				.where(menuImg.repimgYn.eq("Y"))
				.where(menuNmLike(menuSearchDto.getSearchQuery()))
				.fetchOne();
				
						
		return new PageImpl<>(content, pageable, total);
	}
} 
