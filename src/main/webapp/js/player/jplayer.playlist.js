(function(b, f) {
    jPlayerPlaylist = function(a, c, d) {
        var e = this;
        this.playMode = "list-mode";
        this.current = 0;
        this.removing = this.shuffled = this.loop = !1;
        this.cssSelector = b.extend({},
        this._cssSelector, a);
		this.lrcMap =new Map();
        this.options = b.extend(!0, {
            keyBindings: {
                next: {
                    key: 39,
                    fn: function() {
                        e.next()
                    }
                },
                previous: {
                    key: 37,
                    fn: function() {
                        e.previous()
                    }
                }
            }
        },
        this._options, d);
        this.playlist = [];
        this.original = [];
        this._initPlaylist(c);
        this.cssSelector.title = this.cssSelector.cssSelectorAncestor + " .jp-title";
        this.cssSelector.playlist = this.cssSelector.cssSelectorAncestor + " .jp-playlist";
        this.cssSelector.next = this.cssSelector.cssSelectorAncestor + " .jp-next";
        this.cssSelector.previous = this.cssSelector.cssSelectorAncestor + " .jp-previous";
        this.cssSelector.shuffle = this.cssSelector.cssSelectorAncestor + " .jp-shuffle";
        this.cssSelector.shuffleOff = this.cssSelector.cssSelectorAncestor + " .jp-shuffle-off";
        this.cssSelector.randomMode = this.cssSelector.cssSelectorAncestor + " .random-mode";
        this.cssSelector.singleMode = this.cssSelector.cssSelectorAncestor + " .single-mode";
        this.cssSelector.listMode = this.cssSelector.cssSelectorAncestor + " .list-mode";
        this.options.cssSelectorAncestor = this.cssSelector.cssSelectorAncestor;
        this.options.repeat = function(a) {
            e.loop = a.jPlayer.options.loop
        };
        b(this.cssSelector.jPlayer).bind(b.jPlayer.event.ready,
        function() {
            e._init()
        });
        b(this.cssSelector.jPlayer).bind(b.jPlayer.event.ended,
        function() {
            e.next()
        });
        b(this.cssSelector.jPlayer).bind(b.jPlayer.event.play,
        function(a) {
            b(this).jPlayer("pauseOthers");
        });
        b(this.cssSelector.jPlayer).bind(b.jPlayer.event.resize,
        function(a) {
            a.jPlayer.options.fullScreen ? b(e.cssSelector.title).show() : b(e.cssSelector.title).hide()
        });
        var randomModeTemp = null;
        b(this.cssSelector.listMode).click(function() {
           e.playMode = "list-mode";
           b(e.cssSelector.ramdomMode).find("a").removeClass("cur");
           if(randomModeTemp){
        	   b(randomModeTemp).find("a").removeClass("cur");
           }
           b(e.cssSelector.singleMode).find("a").removeClass("cur");
           b(this).find("a").addClass("cur");
        });
        b(this.cssSelector.randomMode).click(function() {
        	randomModeTemp = this;
            e.playMode = "random-mode";
            b(e.cssSelector.singleMode).find("a").removeClass("cur");
            b(e.cssSelector.listMode).find("a").removeClass("cur");
            b(this).find("a").addClass("cur");
         });
        b(this.cssSelector.singleMode).click(function() {
            e.playMode = "single-mode";
            b(e.cssSelector.ramdomMode).find("a").removeClass("cur");
            if(randomModeTemp){
         	   b(randomModeTemp).find("a").removeClass("cur");
            }
            b(e.cssSelector.listMode).find("a").removeClass("cur");
            b(this).find("a").addClass("cur");
         });
        b(this.cssSelector.previous).click(function() {
            e.previous();
            b(this).blur();
            return ! 1
        });
        b(this.cssSelector.next).click(function() {
            e.next();
            b(this).blur();
            return ! 1
        });
        b(this.cssSelector.shuffle).click(function() {
            e.shuffle(!0);
            return ! 1
        });
        b(this.cssSelector.shuffleOff).click(function() {
            e.shuffle(!1);
            return ! 1
        }).hide();
        this.options.fullScreen || b(this.cssSelector.title).hide();
        b(this.cssSelector.playlist + " ul").empty();
        this._createItemHandlers();
        b(this.cssSelector.jPlayer).jPlayer(this.options)
    };
    jPlayerPlaylist.prototype = {
        _cssSelector: {
            jPlayer: "#jquery_jplayer_1",
            cssSelectorAncestor: "#jp_container_1"
        },
        _options: {
            playlistOptions: {
                autoPlay: !1,
                loopOnPrevious: !1,
                shuffleOnLoop: !0,
                enableRemoveControls: !1,
                displayTime: "slow",
                addTime: "fast",
                removeTime: "fast",
                shuffleTime: "slow",
                itemClass: "jp-playlist-item",
                freeGroupClass: "jp-free-media",
                freeItemClass: "jp-playlist-item-free",
                removeItemClass: "jp-playlist-item-remove"
            }
        },
        option: function(a, b) {
            if (b === f) return this.options.playlistOptions[a];
            this.options.playlistOptions[a] = b;
            switch (a) {
            case "enableRemoveControls":
                this._updateControls();
                break;
            case "itemClass":
            case "freeGroupClass":
            case "freeItemClass":
            case "removeItemClass":
                this._refresh(!0),
                this._createItemHandlers()
            }
            return this
        },
        _init: function() {
			//alert("_init");
            var a = this;
            this._refresh(function() {
                a.options.playlistOptions.autoPlay ? a.play(a.current) : a.select(a.current)
            })
        },
        _initPlaylist: function(a) {
            this.current = 0;
            this.removing = this.shuffled = !1;
            this.original = b.extend(!0, [], a);
            this._originalPlaylist()
        },
        _originalPlaylist: function() {
            var a = this;
            this.playlist = [];
            b.each(this.original,
            function(b) {
                a.playlist[b] = a.original[b]
            })
        },
        _refresh: function(a) {
			//alert("_refresh");
            var c = this;
            if (a && !b.isFunction(a)) b(this.cssSelector.playlist + " ul").empty(),
            b.each(this.playlist,
            function(a) {
                b(c.cssSelector.playlist + " ul").append(c._createListItem(c.playlist[a]))
            }),
            this._updateControls();
            else {
                var d = b(this.cssSelector.playlist + " ul").children().length ? this.options.playlistOptions.displayTime: 0;
                b(this.cssSelector.playlist + " ul").slideUp(d,
                function() {
                    var d = b(this);
                    b(this).empty();
                    b.each(c.playlist,
                    function(a) {
                    	d.append(c._createListItem(c.playlist[a]));
						$(".ui-reelList-canvas").append(c._createListItem_fyw(c.playlist[a],a));
                    });
                    c._updateControls();
                    b.isFunction(a) && a();
                    c.playlist.length ? b(this).slideDown(c.options.playlistOptions.displayTime) : b(this).show()
                })
            }
			var height=!this.options.playlistOptions.height? 31 : this.options.playlistOptions.height;
			$(".ui-reelList-canvas").css("height",(height*c.playlist.length)+"px");
			//$(".playlist-length span").html(c.playlist.length);
			$(".ui-reelList-row").dblclick(function(){
				var index = parseInt($(this).attr("reellist-row"));
                c.current !== index ? c.play(index) : b(c.cssSelector.jPlayer).jPlayer("play");
                b(this).blur();
				c.current=index;
				if(addMyplay){
					addMyplay(index);
				}
			});
			$('.ui-reelList-row').hover(function(){
				$(this).addClass("ui-reelList-row-hover");
			},function(){
				$(this).removeClass("ui-reelList-row-hover");
			});
			$(".ui-reelList-centerLoading").css("display","none");
        },
        _createListItem: function(a) {
            var c = this,
            d = "<li><div>",
            d = d + ("<a href='javascript:;' class='" + this.options.playlistOptions.removeItemClass + "'>&times;</a>");
            if (a.free) {
                var e = !0,
                d = d + ("<span class='" + this.options.playlistOptions.freeGroupClass + "'>(");
                b.each(a,
                function(a, f) {
                    b.jPlayer.prototype.format[a] && (e ? e = !1 : d += " | ", d += "<a class='" + c.options.playlistOptions.freeItemClass + "' href='" + f + "' tabindex='1'>" + a + "</a>")
                });
                d += ")</span>"
            }
            d += "<a href='javascript:;' class='" + this.options.playlistOptions.itemClass + "' tabindex='1'>" + a.title + (a.artist ? " <span class='jp-artist'>by " + a.artist + "</span>": "") + "</a>";
            return d += "</div></li>"
        },
		 _createListItem_fyw: function(a,index) {
			/* <div style="top: 0px;" reellist-row="0" class="ui-widget-content ui-reelList-row emptyHeart ui-reelList-active even">
				  <div class="ui-reelList-cell  c0">
					<div class="ui-reelList-checkbox" ><span></span></div> 
					<span class="listening-icon"> </span><span class="ui-reelList-songname">Сƻ��</span></div>
				  <div class="ui-reelList-cell  c1"><a class="a-link" href="javascript:;" target="_blank"> �����ֵ�</a></div>
				  <div class="ui-reelList-cell  c2">��<a class="a-link" href="javascript:;" target="_blank">Сƻ��</a>��</div>
				  <div class="ui-reelList-cell heartColumn c3">
					<div class="playlist-heart"> </div>
				  </div>
			</div>*/
			var height=!this.options.playlistOptions.height? 31 : this.options.playlistOptions.height;
			var top=height*(index);
			var className=(index+1)%2==0? "odd":"even";
			var c = this,
			pItem='<div title="双击播放歌曲" style="top: '+top+'px;" reellist-row="'+index+'" class="ui-widget-content ui-reelList-row emptyHeart '+className+'">';
			//pItem+='<div class="ui-reelList-cell  c0"><div class="ui-reelList-checkbox" ><span></span></div> ';
			pItem+='<div class="ui-reelList-cell  c0"> ';
			pItem+='<span class="listening-icon"> </span><span class="ui-reelList-songname">'+a.title+'</span></div>';
			pItem+='<div class="ui-reelList-cell  c1"><a class="a-link" href="javascript:;"> '+a.artist+'</a></div>';
			pItem+='<div class="ui-reelList-cell  c2"><a class="a-link" href="javascript:;"> '+a.album+'</a></div>';
			//pItem+='<div class="ui-reelList-cell heartColumn c3"><div class="playlist-heart"> </div></div></div>';
			pItem+='<div class="ui-reelList-cell heartColumn c3">';
			pItem+='<img src="images/xin002.svg" width="15" height="15" style="cursor:pointer" title="点赞" id="praise_'+a.id+'" />';
			pItem+='<span id="praise_count_'+a.id+'">'+a.collect+'</span>&nbsp;&nbsp;';
			if(pageFlag!="mycollect"){
				pItem+='<img src="images/add001.svg" width="15" height="15" style="cursor:pointer" title="收藏" id="collect_'+a.id+'" />&nbsp;&nbsp;';
			}
             pItem+='<img src="" width="" height="" style="cursor:pointer" title="下载" id="down_'+a.id+'_'+a.title2+'" />';
			if(pageFlag=="mycollect"){
				pItem+='<img src="images/pk.svg" width="18" height="18" style="cursor:pointer" title="删除" id="del_'+a.id+'_'+a.title2+'" />';
			}
			pItem+='</div></div>';
			
			return pItem;
        },
        _createItemHandlers: function() {
            var a = this;
            b(this.cssSelector.playlist).off("click", "a." + this.options.playlistOptions.itemClass).on("click", "a." + this.options.playlistOptions.itemClass,
            function() {
                var c = b(this).parent().parent().index();
                a.current !== c ? a.play(c) : b(a.cssSelector.jPlayer).jPlayer("play");
                b(this).blur();
                return ! 1
            });
            b(this.cssSelector.playlist).off("click", "a." + this.options.playlistOptions.freeItemClass).on("click", "a." + this.options.playlistOptions.freeItemClass,
            function() {
                b(this).parent().parent().find("." + a.options.playlistOptions.itemClass).click();
                b(this).blur();
                return ! 1
            });
            b(this.cssSelector.playlist).off("click", "a." + this.options.playlistOptions.removeItemClass).on("click", "a." + this.options.playlistOptions.removeItemClass,
            function() {
                var c = b(this).parent().parent().index();
                a.remove(c);
                b(this).blur();
                return ! 1
            })
        },
        _updateControls: function() {
            this.options.playlistOptions.enableRemoveControls ? b(this.cssSelector.playlist + " ." + this.options.playlistOptions.removeItemClass).show() : b(this.cssSelector.playlist + " ." + this.options.playlistOptions.removeItemClass).hide();
            this.shuffled ? (b(this.cssSelector.shuffleOff).show(), b(this.cssSelector.shuffle).hide()) : (b(this.cssSelector.shuffleOff).hide(), b(this.cssSelector.shuffle).show())
        },
        _highlight: function(a) {
            this.playlist.length && a !== f && (b(this.cssSelector.playlist + " .jp-playlist-current").removeClass("jp-playlist-current"),
				b(this.cssSelector.playlist + " li:nth-child(" + (a + 1) + ")").addClass("jp-playlist-current").find(".jp-playlist-item").addClass("jp-playlist-current"),
		b(this.cssSelector.title + " li").html(this.playlist[a].title + (this.playlist[a].artist ? " <span class='jp-artist'>by " + this.playlist[a].artist + "</span>": "")))
        },
        setPlaylist: function(a) {
            this._initPlaylist(a);
           // this._init()
        },
        add: function(a, c) {
            b(this.cssSelector.playlist + " ul").append(this._createListItem(a)).find("li:last-child").hide().slideDown(this.options.playlistOptions.addTime);
            this._updateControls();
            this.original.push(a);
            this.playlist.push(a);
            c ? this.play(this.playlist.length - 1) : 1 === this.original.length && this.select(0)
        },
        remove: function(a) {
            var c = this;
            if (a === f) return this._initPlaylist([]),
            this._refresh(function() {
                b(c.cssSelector.jPlayer).jPlayer("clearMedia")
            }),
            !0;
            if (this.removing) return ! 1;
            a = 0 > a ? c.original.length + a: a;
            0 <= a && a < this.playlist.length && (this.removing = !0, b(this.cssSelector.playlist + " li:nth-child(" + (a + 1) + ")").slideUp(this.options.playlistOptions.removeTime,
            function() {
                b(this).remove();
                if (c.shuffled) {
                    var d = c.playlist[a];
                    b.each(c.original,
                    function(a) {
                        if (c.original[a] === d) return c.original.splice(a, 1),
                        !1
                    })
                } else c.original.splice(a, 1);
                c.playlist.splice(a, 1);
                c.original.length ? a === c.current ? (c.current = a < c.original.length ? c.current: c.original.length - 1, c.select(c.current)) : a < c.current && c.current--:(b(c.cssSelector.jPlayer).jPlayer("clearMedia"), c.current = 0, c.shuffled = !1, c._updateControls());
                c.removing = !1
            }));
            return ! 0
        },
        select: function(a) {
            a = 0 > a ? this.original.length + a: a;
            0 <= a && a < this.playlist.length ? (this.current = a, this._highlight(a), b(this.cssSelector.jPlayer).jPlayer("setMedia", this.playlist[this.current])) : this.current = 0;
			//this.options.playlistOptions.lrc=this.playlist[this.current].lrc;
			
			$(".songname").html(this.playlist[this.current].title);
			$(".log").html(this.playlist[this.current].artist);
			$(".ui-reelList-row").removeClass("ui-reelList-active").eq(a).addClass("ui-reelList-active");
			
			$(".lrcWrap ul").html("");
			
        },
        play: function(a) {
            a = 0 > a ? this.original.length + a: a;
            0 <= a && a < this.playlist.length ? this.playlist.length && (this.select(a), b(this.cssSelector.jPlayer).jPlayer("play")) : a === f && b(this.cssSelector.jPlayer).jPlayer("play")
        },
        pause: function() {
            b(this.cssSelector.jPlayer).jPlayer("pause")
        },
        next: function() {
            var a = this.current + 1 < this.playlist.length ? this.current + 1 : 0;
            this.loop ? 0 === a && this.shuffled && this.options.playlistOptions.shuffleOnLoop && 1 < this.playlist.length ? this.shuffle(!0, !0) : this.play(a) : 0 < a && this.play(a)
        },
        previous: function() {
            var a = 0 <= this.current - 1 ? this.current - 1 : this.playlist.length - 1; (this.loop && this.options.playlistOptions.loopOnPrevious || a < this.playlist.length - 1) && this.play(a)
        },
        shuffle: function(a, c) {
            var d = this;
            a === f && (a = !this.shuffled); (a || a !== this.shuffled) && b(this.cssSelector.playlist + " ul").slideUp(this.options.playlistOptions.shuffleTime,
            function() { (d.shuffled = a) ? d.playlist.sort(function() {
                    return 0.5 - Math.random()
                }) : d._originalPlaylist();
                d._refresh(!0);
                c || !b(d.cssSelector.jPlayer).data("jPlayer").status.paused ? d.play(0) : d.select(0);
                b(this).slideDown(d.options.playlistOptions.shuffleTime)
            })
        }
    }
})(jQuery);