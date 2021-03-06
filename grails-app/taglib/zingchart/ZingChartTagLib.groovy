package zingchart

class ZingChartTagLib {

    static namespace = 'zing'
    
		def include = { attrs ->
		    out << """${javascript(src:"jquery-1.4.2.min.js", plugin:'zing-chart')}"""
		    String jsPath = "${resource(dir:'zingchart', file:'zingchart-1.1.min.js', plugin: 'zing-chart')}"
		    out << """<script type="text/javascript" src="${jsPath}"></script>"""
		}
    
		def chart = { attrs ->
		    def type = attrs.type
		    def libURL = "${resource(dir:'zingchart', file:'zingchart.swf', plugin:'zing-chart')}"
		    def width = attrs.width ? attrs.width.toInteger() : 300
		    def height = attrs.height ? attrs.height.toInteger() : 200
		    def container = attrs.container ?: 'myChart'
		    def xLabels = attrs.xLabels ?: null
		    def yLabels = attrs.yLabels ?: null
		    def data = attrs.data ?: [:]
		    def duration = attrs.duration ? attrs.duration.toFloat() : 1.1F
		    def effect = attrs.effect ? attrs.effect.toInteger() : 1
    
		    zingchart.Chart chart = new zingchart.Chart(type, libURL.toString(), width, height, container, duration, effect)
		    if (xLabels) {
		        chart.setHorizontalLabels(xLabels)
		    }
        if (yLabels) {
            chart.setVerticalLabels(yLabels)
        }
		    data.each { k, v ->
		        chart.addDataSet(v, k)
		    }
		    out << chart.toHTML()
		}

}
