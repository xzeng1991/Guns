package cn.stylefeng.guns.modular.api.entity;

import java.util.List;

import lombok.Data;
@Data
public class MovieInfo {
	private String id;
	private String title;
	private String coverageUrl;
	private String average;
	private List stars;
}
