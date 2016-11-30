<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


<script>
	$(function() {
		$("#container").tabs();
	});

	function auto_load_informe_graphs() {
		$.post(window.location + "graph", {

		}, function(data, status) {
			document.getElementById("tbGraph").innerHTML = data.graph;
		});
	}

	function auto_load() {
		$
				.ajax({
					url : window.location + "body",
					cache : false,
					success : function(data) {
						document.getElementById("tdDataHoraBarramento").innerHTML = data.dataHoraBarramento
						document.getElementById("tdObjReg").innerHTML = data.objReg;
						document.getElementById("tbMensagens").innerHTML = data.mensagens;
					}
				});
	}

	function auto_load_zabbix_graphs() {
		$
				.ajax({
					url : window.location + "body/graphZabbix",
					cache : false,
					success : function(data) {
						document.getElementById("zabbixGraphMemory").src = data.zabbixGraphMemory;
						document.getElementById("zabbixGraphDisco").src = data.zabbixGraphDisco;
						document.getElementById("zabbixGraphCPU").src = data.zabbixGraphCPU;
						document.getElementById("zabbixGraphCPULoad").src = data.zabbixGraphCPULoad;
						document.getElementById("zabbixGraphCPUUtil").src = data.zabbixGraphCPUUtil;
						document.getElementById("zabbixGraphNet").src = data.zabbixGraphNet;
					}
				});
	}

	$(document)
			.ready(
					function() {
						auto_load(); //Call auto_load() function when DOM is Ready
						auto_load_graphs();

						document.getElementById("divLista").style.height = window.innerHeight - 306 + 'px';
						document.getElementById("tbGraph").style.width = window.innerWidth - 200 + 'px';
					});

	//Refresh auto_load() function after 10000 milliseconds
	setInterval(auto_load, 25000);
	setInterval(auto_load_zabbix_graphs, 5000);
	setInterval(auto_load_informe_graphs, 5000);
</script>

<style type="text/css">
body {
	margin: 0px;
}

.ui-tabs {
	padding: 0px;
	top: -1px;
}

.ui-widget.ui-widget-content {
	border: 1px solid #fff;
}

table.ope {
	margin-top: 20px;
	width: 90%;
	align: center;
}

th {
	font-weight: bold;
	border: 1px solid white;
	border-collapse: collapse;
}

th, td.body {
	width: 120px;
	font-size: 15px;
}

td.body {
	text-align: right;
	margin-left: 20px;
}

th.ope, td.ope {
	width: 250px;
}

td.ope {
	text-align: left;
	margin-left: 20px;
}

td.ope, td.body {
	border: 1px solid #fff;
	border-bottom: 1px solid #999;
	border-collapse: collapse;
}

tr.cabecalho {
	color: white;
	background-color: grey;
}

table.cabecalho, table.body {
	padding: 1;
	width: 800px;
	border: 1px solid black;
	border-collapse: collapse;
}

div.divLista {
	width: 820px;
	height: 150px;
	overflow: auto;
}

table.body {
	font-size: 14px;
	overflow: auto;
}

.graficos td {
	vertical-align: top;
	background-color: #333;
	border: 1px solid #333;
}

table.graficosInformes {
	overflow: auto;
	align: center;
	margin-top:40px;
}

div.simpleTabs {
	width: 100%;
}

.tableGraficos td img {
	width: 100%;
	overflow: auto;
	margin-top: 45px;
}

.disabled {
	pointer-events: none;
	opacity: 0.6;
}
</style>
</head>
<body>
	<div id="container" class="simpleTabs">
		<div
			style="width: 100%; float: left; border-bottom: 1px solid #ccc; position: fixed; background-color: white;">
			<img style="float: left; height: 50px; margin-left: 25px;"
				src='<c:url value="/resources/images/intec2_logo.png"/>' />
			<ul class="simpleTabsNavigation"
				style="float: right; position: relative; top: 5px;">
				<li><a href="#tabs-1">Operações Militares</a></li>
				<li><a href="#tabs-2">Gráficos</a></li>
				<li><a href="#tabs-3">Infraestrutura de TI</a></li>
				<li class="disabled"><a href="#tabs-4">Relatório de
						Auditoria</a></li>
			</ul>
		</div>
		<div class="clear:both;"></div>
		<div id="tabs-1" class="simpleTabsContent" style="margin-top: 55px;">
			<table>
				<tr>
					<td><b>Data e hora do barramento: </b></td>
					<td id="tdDataHoraBarramento">${dataHoraBarramento}</td>
				</tr>
				<tr>
					<td><b>Quantidade de objetos registrados no barramento: </b></td>
					<td id="tdObjReg">${objReg}</td>
				</tr>
			</table>

			<table class="ope">
				<tr>
					<td align="center"><b>Lista de Operações</b></td>
				</tr>

				<tr>
					<td align="center">
						<table class="cabecalho">
							<tr class="cabecalho">
								<th class="ope"><b>Operação</b></th>
								<th><b>Adjudicações</b></th>
								<th><b>Informes</b></th>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td align="center">
						<div id="divLista" class="divLista">
							<table id="tbMensagens" class="body">
								<c:forEach items="${mensagens}" var="mensagem">
									<tr>
										<td class="ope">${mensagem.operacao}</td>
										<td class="body">${mensagem.totalAdjudicacoes}</td>
										<td class="body">${mensagem.totalInformes}</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div id="tabs-2" class="simpleTabsContent graficos">
			<table id="tbGraph" class="graficosInformes">
				<tr>
					<td id="tdGraph"><img id="graph" alt="" src="${graph}" /></td>
				</tr>
			</table>
		</div>
		<div id="tabs-3" class="simpleTabsContent graficos">
			<table class="tableGraficos">
				<tr>
					<td class="zabbix"><img id="zabbixGraphMemory" class="zabbix"
						src="${zabbixGraphMemory}" /></td>
					<td class="zabbix"><img id="zabbixGraphDisco" class="zabbix"
						src="${zabbixGraphDisco}" /></td>
				</tr>
				<tr>
					<td class="zabbix"><img id="zabbixGraphCPU" class="zabbix"
						src="${zabbixGraphCPU}" /></td>
					<td class="zabbix"><img id="zabbixGraphCPULoad" class="zabbix"
						src="${zabbixGraphCPULoad}" /></td>
				</tr>
				<tr>
					<td class="zabbix"><img id="zabbixGraphCPUUtil" class="zabbix"
						src="${zabbixGraphCPUUtil}" /></td>
					<td class="zabbix"><img id="zabbixGraphNet" class="zabbix"
						src="${zabbixGraphNet}" /></td>
				</tr>
			</table>
		</div>
		<div id="tabs-4" class="simpleTabsContent graficos"></div>
	</div>
</body>
</html>