package cn.stylefeng.guns.modular.api.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.druid.sql.ast.expr.SQLCaseExpr.Item;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.stylefeng.guns.core.util.HttpClientUtil;
import cn.stylefeng.guns.modular.api.entity.MovieInfo;

@Service
public class MovieService {
	private static Logger logger = LoggerFactory.getLogger(MovieService.class);
	private static String DOUBANBASE = "https://douban.uieee.com/v2/movie/";
	private static String IN_THEATER_PATH = "in_theaters";
	private static String COMING_SOON_PATH = "coming_soon";
	private static String TOP250_PATH = "top250";
	
	private List<MovieInfo> queryDouban(String path, Integer start, Integer count) {
		List<MovieInfo> movieList = new ArrayList<>();

		StringBuilder queryUrl = new StringBuilder();
		queryUrl.append(DOUBANBASE).append(path);
		queryUrl.append("?start=").append(start);
		queryUrl.append("&count=").append(count);
		
		logger.info("豆瓣请求：" + queryUrl);
		String resp = HttpClientUtil.doGet(queryUrl.toString());
		logger.info("豆瓣返回：" + resp);
		
		if (StringUtils.isEmpty(resp)) {
			throw new RuntimeException("豆瓣API没有返回数据");
		}

		JSONObject ret = JSON.parseObject(resp);
		JSONArray array = ret.getJSONArray("subjects");
		if (array != null && array.size() > 0) {
			JSONObject item = null;
			MovieInfo movieInfo = null;
			for (int i = 0; i < array.size(); i++) {
				item = array.getJSONObject(i);
				movieInfo = new MovieInfo();

				movieInfo.setId(item.getString("id"));
				movieInfo.setTitle(item.getString("title"));
				movieInfo.setCoverageUrl(item.getJSONObject("images").getString("large"));
				movieInfo.setAverage(item.getJSONObject("rating").getString("average"));
				Integer stars = item.getJSONObject("rating").getInteger("stars");
				movieInfo.setStars(convertToStarsArray(stars));

				movieList.add(movieInfo);
			}
		}
		
		return movieList;
	}
	
	private List<String> convertToStarsArray(Integer stars) {
		Integer num = stars / 10;
		Integer mod = stars % 10;
		List<String> array = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			if (i <= num) {
				array.add("1");
			} else {
				if (mod == 5) {
					array.add("0.5");
				} else {
					array.add("0");
				}
			}
		}
		return array;
	}
	
	public List<MovieInfo> queryInTheater(Integer start, Integer count){
		//String listStr = "[{\"average\":\"8.8\",\"coverageUrl\":\"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2552058346.jpg\",\"id\":\"26100958\",\"stars\":[\"1\",\"1\",\"1\",\"1\",\"0.5\"],\"title\":\"复仇者联盟4：终局之战\"},{\"average\":\"6.3\",\"coverageUrl\":\"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2554545271.jpg\",\"id\":\"26899146\",\"stars\":[\"1\",\"1\",\"1\",\"0.5","0.5"],"title":"雪暴"},{"average":"8.8","coverageUrl":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2554905337.jpg","id":"30170448","stars":["1","1","1","1","0.5"],"title":"何以为家"}]";
		/*List<MovieInfo> movieList = queryDouban(IN_THEATER_PATH, start, count);
		logger.info("inTheater: " + JSON.toJSONString(movieList));*/
		
		List<MovieInfo> movieList = new ArrayList<>();
		MovieInfo item = new MovieInfo();
		item.setId("1016681");
		item.setTitle("复仇者联盟4：终局之战");
		item.setCoverageUrl("https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2552058346.jpg");
		item.setAverage("8.8");
		item.setStars(convertToStarsArray(45));
		movieList.add(item);
		
		item = new MovieInfo();
		item.setId("26899146");
		item.setTitle("雪暴");
		item.setCoverageUrl("https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2554545271.jpg");
		item.setAverage("6.3");
		item.setStars(convertToStarsArray(40));
		movieList.add(item);
		
		item = new MovieInfo();
		item.setId("30170448");
		item.setTitle("何以为家");
		item.setCoverageUrl("https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2554905337.jpg");
		item.setAverage("8.8");
		item.setStars(convertToStarsArray(50));
		movieList.add(item);
		
		return movieList;
	}
	
	public List<MovieInfo> queryComingSoon(Integer start, Integer count){
		/*List<MovieInfo> movieList = queryDouban(COMING_SOON_PATH, start, count);
		logger.info("comingSoon: " + JSON.toJSONString(movieList));*/
		List<MovieInfo> movieList = new ArrayList<>();
		MovieInfo item = new MovieInfo();
		item.setId("30230160");
		item.setTitle("港珠澳大桥");
		item.setCoverageUrl("https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2554017175.jpg");
		item.setAverage("0");
		item.setStars(convertToStarsArray(0));
		movieList.add(item);
		
		item = new MovieInfo();
		item.setId("26311974");
		item.setTitle("下一任：前任");
		item.setCoverageUrl("https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2554795775.jpg");
		item.setAverage("0");
		item.setStars(convertToStarsArray(0));
		movieList.add(item);
		
		item = new MovieInfo();
		item.setId("26878827");
		item.setTitle("悟空奇遇记");
		item.setCoverageUrl("https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2552558351.jpg");
		item.setAverage("0");
		item.setStars(convertToStarsArray(0));
		movieList.add(item);
		return movieList;
	}
	
	public List<MovieInfo> queryTop250(Integer start, Integer count){
		/*List<MovieInfo> movieList = queryDouban(TOP250_PATH, start, count);
		logger.info("top250: " + JSON.toJSONString(movieList));*/
		List<MovieInfo> movieList = new ArrayList<>();
		MovieInfo item = new MovieInfo();
		item.setId("1292052");
		item.setTitle("肖申克的救赎");
		item.setCoverageUrl("https://img3.doubanio.com/view/photo/s_ratio_poster/public/p480747492.jpg");
		item.setAverage("9.6");
		item.setStars(convertToStarsArray(50));
		movieList.add(item);
		
		item = new MovieInfo();
		item.setId("1291546");
		item.setTitle("霸王别姬");
		item.setCoverageUrl("https://img3.doubanio.com/view/photo/s_ratio_poster/public/p1910813120.jpg");
		item.setAverage("9.6");
		item.setStars(convertToStarsArray(50));
		movieList.add(item);
		
		item = new MovieInfo();
		item.setId("1295644");
		item.setTitle("这个杀手不太冷");
		item.setCoverageUrl("https://img3.doubanio.com/view/photo/s_ratio_poster/public/p511118051.jpg");
		item.setAverage("9.4");
		item.setStars(convertToStarsArray(50));
		movieList.add(item);
		return movieList;
	}
}
