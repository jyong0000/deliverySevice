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
//	public Page<Menu> getAdminMenuPage(MenuSearchDto menuSearchDto, Pageable pageable){
//		List<Menu> content = queryFactory
//							.selectFrom(QMenu.menu)
//						
//	}
//} 
