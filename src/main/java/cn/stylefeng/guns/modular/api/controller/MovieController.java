package cn.stylefeng.guns.modular.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.stylefeng.guns.modular.api.entity.MovieInfo;
import cn.stylefeng.guns.modular.api.service.MovieService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.SuccessResponseData;

@RestController
@RequestMapping("/gunsApi/movie")
public class MovieController extends BaseController{
	@Autowired
	private MovieService movieService;
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
    public Object list() {
		Map<String, Object> map = new HashMap<>();
		List<MovieInfo> inTheaters = movieService.queryInTheater(0, 3);
		List<MovieInfo> comingSoons = movieService.queryComingSoon(0, 3);
		List<MovieInfo> top250s = movieService.queryTop250(0, 3);
		
		map.put("inTheaters", inTheaters);
		map.put("comingSoons", comingSoons);
		map.put("top250s", top250s);
		
        return new SuccessResponseData(map);
    }
}
