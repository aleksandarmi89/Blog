package com.cubes.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.cubes.main.entity.Slider;



@Repository
public interface SliderRepository extends JpaRepository<Slider, Integer> {
	
 public List<Slider> findAllByEnabledTrueOrderByPriorityDesc();
 
 
 
 public List<Slider> findFirst3ByEnabledTrueOrderByPriorityDesc();
}
