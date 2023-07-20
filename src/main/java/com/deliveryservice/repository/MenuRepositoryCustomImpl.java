//package com.deliveryservice.repository;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.thymeleaf.util.StringUtils;
//
//import com.deliveryservice.constant.MenuStatus;
//import com.deliveryservice.dto.MenuSearchDto;
//import com.deliveryservice.entity.Menu;
//import com.deliveryservice.entity.QMenu;
//import com.querydsl.core.types.dsl.BooleanExpression;
//import com.querydsl.core.types.dsl.Wildcard;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//
//import jakarta.persistence.EntityManager;
//
//public class MenuRepositoryCustomImpl implements MenuRepositoryCustom{
//	
//	private JPAQueryFactory queryFactory;
//	
//	public MenuRepositoryCustomImpl(EntityManager em) {
//		this.queryFactory = new JPAQueryFactory(em);
//	}
//	
//	private BooleanExpression regDtsAfter(String searchDateType) {
//		LocalDateTime dateTime = LocalDateTime.now();
//		
//		if(StringUtils.equals("all", searchDateType) || searchDateType == null) 
//			return null;
//		else if(StringUtils.equals("1d", searchDateType))
//			dateTime = dateTime.minusDays(1); //현재 날짜로부터 1일전
//		else if(StringUtils.equals("1w", searchDateType))
//			dateTime = dateTime.minusWeeks(1); //1주일 전
//		else if(StringUtils.equals("1m", searchDateType))
//			dateTime = dateTime.minusMonths(1); //1달전
//		else if(StringUtils.equals("6m", searchDateType))
//			dateTime = dateTime.minusMonths(6); //6개월전
//		
//		return QMenu.menu.regTime.after(dateTime); //Q객체 리턴
//	}
//	
//	private BooleanExpression searchMenuStatusEq(MenuStatus searchMenuStatus) {
//		return searchMenuStatus == null ? null :
//			QMenu.menu.menuStatus.eq(searchMenuStatus);
//	}
//	
//	private BooleanExpression searchByLike(String searchBy, String searchQuery) {
//		if(StringUtils.equals("menuNm", searchBy)) {
//			return QMenu.menu.menuNm.like("%" + searchQuery + "%");
//		}else if(StringUtils.equals("createdBy", searchBy)) {
//			return QMenu.menu.createdBy.like("%" + searchQuery + "%");
//		}
//		return null;
//	}
//	
//	@Override
//	public Page<Menu> getAdminPage(MenuSearchDto menuSearchDto, Pageable pageable){
//		List<Menu> content = queryFactory
//							.selectFrom(QMenu.menu)
//							.where(regDts)
//	}
//} 
