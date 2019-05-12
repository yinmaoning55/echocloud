!function(y,q){var k,z=/\n|\r/,g=/\[[\s\S]*?\]/,j=/\[\d{2,}:\d{2}(?:[\.|:]\d{2,5})?\]/g,b=/\[\d{2,}:\d{2}(?:[\.|:]\d{2,5})?\]/,d=/\[offset:[+|-]?\d+?(?=\])/,m="ui-lrc",w="ui-lrc-sentence",x="ui-lrc-current",v=function(a){return b.test(a)},p=function(c){var h=c.split(":"),a=h[0],f,l;if(h.length===3){f=h[1];l=h[2]}else{h=h[1].split(".");f=h[0];l=h[1]}return ~~a*60*1000+~~f*1000+~~l};y.widget("ui.lrc",{version:"1.0.0",widgetEventPrefix:"lrc",options:{lrc:"",scrollAnimate:"",orientation:"vertical"},_create:function(){this.element.addClass(m);this.$ul=y("<ul>").appendTo(this.element);this.autoScroll=true;if(this.options.lrc){this._parse();this._render()}this.element.addClass("ui-lrc-"+this.options.orientation);var a=this;if(y.fn.mousewheel){this.element.mousewheel(function(){if(a.autoScroll){a.autoScroll=false;a.element.bind("mouseleave.lrcwheel",function(){a.autoScroll=true;a.element.unbind("mouseleave.lrcwheel")})}})}this.element.mousedown(function(){if(a.autoScroll){a.autoScroll=false;y(document).bind("mouseup.lrc",function(c){a.autoScroll=true;y(document).unbind("mouseup.lrc")})}});this.element.bind("mouseleave.lrcup",function(){a.autoScroll=true})},_parse:function(){var a=this.options.lrc;if(typeof a!=="string"||!(a=y.trim(a))){this._clear();return}if(v(a)){this._parseLrc(a)}else{this._parseTxt(a)}},_parseLrc:function(t){var l=[],f=0,s,C,E,B,i,A,o,D;if(k=t.match(d)){f=~~k[0].slice(8)}s=t.split(z);for(i=0,o=s.length;i<o;i++){C=s[i];if(k=C.match(j)){for(A=0,D=k.length;A<D;A++){E=p(k[A].slice(1,-1))+f;B=y.trim(C.split(k.join("")).join(""));l.push([E,B,null])}}}if(l.length){this.parsed=l.sort(function(a,c){return a[0]-c[0]});this._setStatus("lrc")}else{this._setStatus("no-lrc")}},_parseTxt:function(s){var c=[],a,n=s.replace(g,"").split(z),t,f;for(t=0,f=n.length;t<f;t++){a=y.trim(n[t]);if(a){c.push([-1,a,null])}}if(c.length){this.parsed=c;this._setStatus("txt-lrc")}else{this._setStatus("no-lrc")}},_setStatus:function(a){this.element.removeClass(this.status||"lrc").addClass(a);this.status=a},_render:function(){if(!this.parsed||!this.parsed.length){this._clear();return}var f,a,c,h;this.$ul.empty();for(c=0,h=this.parsed.length;c<h;c++){a=this.parsed[c];f=y("<li>").addClass(w).html(a[1]||"&nbsp;").appendTo(this.$ul);a[2]=f}},_clear:function(){this.$ul.empty();this.$ul.append("<li>\u7487\u30e6\u74d5\u93c7\u53c9\u6b8f\u93c3\u8235\u75c5\u93c8\u590b\u74d5\u7487\ufffd/li>");this.parsed=null;this._setStatus("no-lrc")},_setOption:function(a,c){this._super.apply(this,arguments);switch(a){case"orientation":this.element.removeClass("ui-lrc-horizontal ui-lrc-vertical").addClass("ui-lrc-"+c);break;case"lrc":this.setLrc(c);break}},_findLine:function(c){if(!this.parsed||!this.parsed.length){return -1}var h=this.parsed,a=0,f=h.length,l=Math.floor(f/2);if(c<h[0][0]){return -1}while(!(h[l][0]<=c&&c<(h[l+1]?h[l+1][0]:999999999))){if(c<h[l][0]){f=l-1}else{a=l+1}l=Math.floor((a+f)/2)}return l},setLrc:function(a){this.options.lrc=a;this._parse();this._render()},scrollTo:function(c){c=~~c;if(!c||this.status!=="lrc"){return}var f=this.options.orientation==="vertical",a=this._findLine(c);if(a===-1){if(this.autoScroll){if(typeof this["_animate_"+this.options.scrollAnimate]==="function"){this["_animate_"+this.options.scrollAnimate](0,-1)}else{this.element[f?"scrollTop":"scrollLeft"](0)}}return}if(this.$curLine&&this.$curLine!=this.parsed[a][2]){this.$curLine.removeClass(x);this.$curLine=null}this.$curLine=this.parsed[a][2];this.$curLine.addClass(x);if(this.autoScroll){if(typeof this["_animate_"+this.options.scrollAnimate]==="function"){this["_animate_"+this.options.scrollAnimate](c,a)}else{this.element[[f?"scrollTop":"scrollLeft"]](f?this.$curLine.offset().top-this.$ul.offset().top-this.element.height()*0.381967+this.$curLine.outerHeight(true)*0.618033:this.$curLine.offset().left-this.$ul.offset().left-this.element.width()*0.5+this.$curLine.outerWidth(true)*0.5)}}}})}(jQuery);!function(a,b){a.widget("ui.lrc",a.ui.lrc,{_setOption:function(d,c){this._super.apply(this,arguments);switch(d){case"orientation":case"scrollAnimate":this.$ul.css({paddingTop:"",paddingBottom:"",paddingLeft:"",paddingRight:""});this.ani_curLineNum=b;this.lastMs=null;break}},setLrc:function(){this._super.apply(this,arguments);this.ani_curLineNum=b;this.lastMs=null},_animate_line:function(d,c){var e=this.options.orientation==="vertical";if(c===-1&&this.ani_curLineNum===b){this.element.stop(1,0).animate(e?{scrollTop:0}:{scrollLeft:0},500);return}var f=e?this.$curLine.offset().top-this.$ul.offset().top-this.element.height()*0.5+this.$curLine.outerHeight(true)*0.5:this.$curLine.offset().left-this.$ul.offset().left-this.element.width()*0.5+this.$curLine.outerWidth(true)*0.5;if(this.ani_curLineNum===b||this.ani_curLineNum!=c){this.element.stop(1,0).animate(e?{scrollTop:f}:{scrollLeft:f},500)}this.ani_curLineNum=c},_animate_uniform:function(v,m){var j=this.options.orientation==="vertical",w=this.element[j?"height":"width"](),f=this.parsed[m],g=this.parsed[m+1],c,d,k,p,q;if(m===-1){this.element[j?"scrollTop":"scrollLeft"](0);return}this.$ul.css(j?{paddingTop:w*0.381967,paddingBottom:w*0.618033,paddingLeft:"",paddingRight:""}:{paddingTop:"",paddingBottom:"",paddingLeft:w*(1-0.618033),paddingRight:w*0.618033});p=this.$ul.offset()[j?"top":"left"];d=this.$curLine.offset()[j?"top":"left"]-p;k=g?g[2].offset()[j?"top":"left"]-p:0;c=k?k-d:this.$curLine[j?"outerHeight":"outerWidth"](true);q=d+c*(v-f[0])/(g?g[0]-f[0]:999999999)-w*0.381967;if(q>=0){this.element.stop(1,1);if(this.lastMs&&0<v-this.lastMs&&v-this.lastMs<400){this.element.stop(true,true).animate(j?{scrollTop:q}:{scrollLeft:q},v-this.lastMs,"linear")}else{this.element[j?"scrollTop":"scrollLeft"](q)}this.lastMs=v}}})}(jQuery);
