package com.deliveryservice.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import com.deliveryservice.constant.MenuStatus;
import com.deliveryservice.dto.MenuSearchDto;
import com.deliveryservice.entity.Menu;
import com.deliveryservice.entity.QMenu;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

public class MenuRepositoryCustomImpl implements MenuRepositoryCustom{

}
