package com.deliveryservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
	}
