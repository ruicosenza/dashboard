package br.com.dashboard.repositories;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import org.springframework.web.servlet.ModelAndView;

import br.com.dashboard.conf.ConfigProperties;

public class ZabbixGraphComponent {

	private String getZabbixGraph(String graphId) {

		String base_url = "";
		ConfigProperties confProp = new ConfigProperties();
		Properties prop = confProp.getProperty();

		base_url = prop.getProperty("zabbix_url");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String dataHora = sdf.format(new Date());
		String url = base_url + graphId
				+ "&screenid=20&height=120&width=480&legend=1&show_legend=1&legend=1&updateProfile=1&profileIdx=&profileIdx2=&period=3600&stime="
				+ dataHora + " &sid=c69f67669dc18e30";

		return url;
	}
	
	public ModelAndView montaZabbixGraph( ModelAndView modelAndView ){
		String zabbixGraphMemory = getZabbixGraph("graphid=548");
		String zabbixGraphDisco = getZabbixGraph("graphid=550");
		String zabbixGraphCPU = getZabbixGraph("graphid=549");
		String zabbixGraphCPULoad = getZabbixGraph("graphid=554");
		String zabbixGraphCPUUtil = getZabbixGraph("graphid=557");
		String zabbixGraphNet = getZabbixGraph("graphid=558");

		modelAndView.addObject("zabbixGraphMemory", zabbixGraphMemory);
		modelAndView.addObject("zabbixGraphDisco", zabbixGraphDisco);
		modelAndView.addObject("zabbixGraphCPU", zabbixGraphCPU);
		modelAndView.addObject("zabbixGraphCPULoad", zabbixGraphCPULoad);
		modelAndView.addObject("zabbixGraphCPUUtil", zabbixGraphCPUUtil);
		modelAndView.addObject("zabbixGraphNet", zabbixGraphNet);
		
		return modelAndView;
	}
	
	public HashMap<String, Object> montaZabbixGraph( HashMap<String, Object> map ){
		String zabbixGraphMemory = getZabbixGraph("graphid=548");
		String zabbixGraphDisco = getZabbixGraph("graphid=550");
		String zabbixGraphCPU = getZabbixGraph("graphid=549");
		String zabbixGraphCPULoad = getZabbixGraph("graphid=554");
		String zabbixGraphCPUUtil = getZabbixGraph("graphid=557");
		String zabbixGraphNet = getZabbixGraph("graphid=558");

		
		
		map.put("zabbixGraphMemory", zabbixGraphMemory);
		map.put("zabbixGraphDisco", zabbixGraphDisco);
		map.put("zabbixGraphCPU", zabbixGraphCPU);
		map.put("zabbixGraphCPULoad", zabbixGraphCPULoad);
		map.put("zabbixGraphCPUUtil", zabbixGraphCPUUtil);
		map.put("zabbixGraphNet", zabbixGraphNet);
		
		return map;
	}
}