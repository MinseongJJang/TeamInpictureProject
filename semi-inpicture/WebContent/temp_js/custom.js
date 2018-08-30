/* JS Document */

/******************************

[Table of Contents]

1. Vars and Inits
2. Set Header
3. Init Menu
4. Init Timer
5. Init Favorite
6. Init Fix Product Border
7. Init Isotope Filtering
8. Init Slider


******************************/

jQuery(document).ready(function($)
{
	"use strict";

	/* 

	1. Vars and Inits

	*/

	var header = $('.header');
	var topNav = $('.top_nav')
	var mainSlider = $('.main_slider');
	var hamburger = $('.hamburger_container');
	var menu = $('.hamburger_menu');
	var menuActive = false;
	var hamburgerClose = $('.hamburger_close');
	var fsOverlay = $('.fs_menu_overlay');

	setHeader();

	$(window).on('resize', function()
	{
		initFixProductBorder();
		setHeader();
	});

	$(document).on('scroll', function()
	{
		setHeader();
	});

	initMenu();
	initTimer();
	initFavorite();
	initFixProductBorder();
	initIsotopeFiltering();
	initSlider();

	/* 

	2. Set Header

	*/

	function setHeader()
	{
		if(window.innerWidth < 992)
		{
			if($(window).scrollTop() > 100)
			{
				header.css({'top':"0"});
			}
			else
			{
				header.css({'top':"0"});
			}
		}
		else
		{
			if($(window).scrollTop() > 100)
			{
				header.css({'top':"-0px"});
			}
			else
			{
				header.css({'top':"0"});
			}
		}
		if(window.innerWidth > 991 && menuActive)
		{
			closeMenu();
		}
	}

	/* 

	3. Init Menu

	*/

	function initMenu()
	{
		if(hamburger.length)
		{
			hamburger.on('click', function()
			{
				if(!menuActive)
				{
					openMenu();
				}
			});
		}

		if(fsOverlay.length)
		{
			fsOverlay.on('click', function()
			{
				if(menuActive)
				{
					closeMenu();
				}
			});
		}

		if(hamburgerClose.length)
		{
			hamburgerClose.on('click', function()
			{
				if(menuActive)
				{
					closeMenu();
				}
			});
		}

		if($('.menu_item').length)
		{
			var items = document.getElementsByClassName('menu_item');
			var i;

			for(i = 0; i < items.length; i++)
			{
				if(items[i].classList.contains("has-children"))
				{
					items[i].onclick = function()
					{
						this.classList.toggle("active");
						var panel = this.children[1];
					    if(panel.style.maxHeight)
					    {
					    	panel.style.maxHeight = null;
					    }
					    else
					    {
					    	panel.style.maxHeight = panel.scrollHeight + "px";
					    }
					}
				}	
			}
		}
	}

	function openMenu()
	{
		menu.addClass('active');
		// menu.css('right', "0");
		fsOverlay.css('pointer-events', "auto");
		menuActive = true;
	}

	function closeMenu()
	{
		menu.removeClass('active');
		fsOverlay.css('pointer-events', "none");
		menuActive = false;
	}

	/* 

	4. Init Timer

	*/

	function initTimer()
    {
    	if($('.timer').length)
    	{
    		// Uncomment line below and replace date
	    	// var target_date = new Date("Dec 7, 2017").getTime();

	    	// comment lines below
	    	var date = new Date();
	    	date.setDate(date.getDate() + 3);
	    	var target_date = date.getTime();
	    	//----------------------------------------
	 
			// variables for time units
			var days, hours, minutes, seconds;

			var d = $('#day');
			var h = $('#hour');
			var m = $('#minute');
			var s = $('#second');

			setInterval(function ()
			{
			    // find the amount of "seconds" between now and target
			    var current_date = new Date().getTime();
			    var seconds_left = (target_date - current_date) / 1000;
			 
			    // do some time calculations
			    days = parseInt(seconds_left / 86400);
			    seconds_left = seconds_left % 86400;
			     
			    hours = parseInt(seconds_left / 3600);
			    seconds_left = seconds_left % 3600;
			     
			    minutes = parseInt(seconds_left / 60);
			    seconds = parseInt(seconds_left % 60);

			    // display result
			    d.text(days);
			    h.text(hours);
			    m.text(minutes);
			    s.text(seconds); 
			 
			}, 1000);
    	}	
    }

    /* 

	5. Init Favorite

	*/

    function initFavorite()
    {
    	if($('.favorite').length)
    	{
    		var favs = $('.favorite');

    		favs.each(function()
    		{
    			var fav = $(this);
    			var active = false;
    			if(fav.hasClass('active'))
    			{
    				active = true;
    			}

    			fav.on('click', function()
    			{
    				if(active)
    				{
    					fav.removeClass('active');
    					active = false;
    				}
    				else
    				{
    					fav.addClass('active');
    					active = true;
    				}
    			});
    		});
    	}
    }

    /* 

	6. Init Fix Product Border

	*/

    function initFixProductBorder()
    {
    	if($('.product_filter').length)
    	{
			var products = $('.product_filter:visible');
    		var wdth = window.innerWidth;

    		// reset border
    		products.each(function()
    		{
    			$(this).css('border-right', 'solid 1px #e9e9e9');
    		});

    		// if window width is 991px or less

    		if(wdth < 480)
			{
				for(var i = 0; i < products.length; i++)
				{
					var product = $(products[i]);
					product.css('border-right', 'none');
				}
			}

    		else if(wdth < 576)
			{
				if(products.length < 5)
				{
					var product = $(products[products.length - 1]);
					product.css('border-right', 'none');
				}
				for(var i = 1; i < products.length; i+=2)
				{
					var product = $(products[i]);
					product.css('border-right', 'none');
				}
			}

    		else if(wdth < 768)
			{
				if(products.length < 5)
				{
					var product = $(products[products.length - 1]);
					product.css('border-right', 'none');
				}
				for(var i = 2; i < products.length; i+=3)
				{
					var product = $(products[i]);
					product.css('border-right', 'none');
				}
			}

    		else if(wdth < 992)
			{
				if(products.length < 5)
				{
					var product = $(products[products.length - 1]);
					product.css('border-right', 'none');
				}
				for(var i = 3; i < products.length; i+=4)
				{
					var product = $(products[i]);
					product.css('border-right', 'none');
				}
			}

			//if window width is larger than 991px
			else
			{
				if(products.length < 5)
				{
					var product = $(products[products.length - 1]);
					product.css('border-right', 'none');
				}
				for(var i = 4; i < products.length; i+=5)
				{
					var product = $(products[i]);
					product.css('border-right', 'none');
				}
			}	
    	}
    }

    /* 

	7. Init Isotope Filtering

	*/

    function initIsotopeFiltering()
    {
    	$('.product-grid').isotope({
            filter: '.women',
            animationOptions: {
                duration: 750,
                easing: 'linear',
                queue: false
            }
    	   });
    	if($('.grid_sorting_button').length)
    	{
    		$('.grid_sorting_button').click(function()
	    	{
    			
	    		// putting border fix inside of setTimeout because of the transition duration
	    		setTimeout(function()
		        {
		        	initFixProductBorder();
		        },500);

		        $('.grid_sorting_button.active').removeClass('active');
		        $(this).addClass('active');
		       
		        var selector = $(this).attr('data-filter');
		        
		        $('.product-grid').isotope({
		            filter: selector,
		            animationOptions: {
		                duration: 750,
		                easing: 'linear',
		                queue: false
		            }
		        });

		        
		         return false;
		    });
    	}
    }

    /* 

	8. Init Slider

	*/

    function initSlider()
    {
    	if($('.product_slider').length)
    	{
    		var slider1 = $('.product_slider');

    		slider1.owlCarousel({
    			loop:false,
    			dots:false,
    			nav:false,
    			responsive:
				{
					0:{items:1},
					480:{items:2},
					768:{items:3},
					991:{items:4},
					1280:{items:5},
					1440:{items:5}
				}
    		});

    		if($('.product_slider_nav_left').length)
    		{
    			$('.product_slider_nav_left').on('click', function()
    			{
    				slider1.trigger('prev.owl.carousel');
    			});
    		}

    		if($('.product_slider_nav_right').length)
    		{
    			$('.product_slider_nav_right').on('click', function()
    			{
    				slider1.trigger('next.owl.carousel');
    			});
    		}
    	}
    }
    
    // main carousel
    /* 3d Carousel image slider */

    (function ( window ) {
        'use strict';


        /* Carousel constructor class */

        var Carousel = function ( el, options ) {
            var that = this;

            this._settings = options || { };

            // options
            this._settings.startingFace = this._settings.startingFace || 1;
            this._settings.autoplay = this._settings.autoplay || false;
            this._settings.slideshowInterval = this._settings.slideshowInterval || 3000;

            this._element = document.getElementById( el );
            this._dragArea = document.body;
            this._navButtons = document.querySelectorAll( '.navigation-control' );
            this._panelCount = this._element.children.length;
            this._theta = 0;
            this._currentFace = 0;
            this._segmentSize = 360 / this._panelCount;
            this._lastDragX = null;
            this._autoplayTimeout = null;


            var i = 0,
                j = 0,
                len = this._element.children.length,
                buttonLen = this._navButtons.length;
          
            for ( ; i < len; i++ ) {
               
                  this._element.children[ i ].style[ 'transform' ] =
                                    'rotateY( ' + ( i * ( this._segmentSize ) ) + 'deg )' +
                                    'translateZ( ' + this._getTranslateZ() + 'px )';
            }

            for ( ; j < buttonLen; j++ ) {
                this._navButtons[ j ].addEventListener( 'click', function ( event ) {

                    var value = event.target.getAttribute( 'data-increment' );
                    that._rotateWheel( value );
                }, false );
            }

            this._element.addEventListener( 'touchmove', function (e) {
                e.preventDefault();
                var currentX = e.touches[0].clientX;
                var dragValue = currentX > that._lastDragX ? 1 : -1;

                that._dragRotate( dragValue );
                that._lastDragX = currentX;
            });


            this._element.addEventListener( 'touchend', function (e) {
                that._theta = Math.round( that._theta / that._segmentSize ) * that._segmentSize;
                that._rotateWheel( 0 );
            });


            this._rotateWheel( this._settings.startingFace );

            if ( this._settings.autoplay ) {
                this.play();
            }
        };




        /* Returns translateZ value based on size and panel count */

        Carousel.prototype._getTranslateZ = function () {
            return Math.round( ( this._element.children[ 0 ].clientWidth / 2 ) / Math.tan( Math.PI / this._panelCount ) );
        };



        /* Returns number of front facing slide in gallery */

        Carousel.prototype._getFaceNumber = function ( angle ) {
            if ( angle > 360 || angle < -359) {
                return this._getFaceNumber( ( Math.abs( angle ) ) - 360 );
            } else {
                if ( this._theta < 1 ) {
                    return Math.abs( this._panelCount / ( 360 / angle ) ) + 1;
                } else {
                    return Math.abs( this._panelCount / ( 360 / angle ) );
                }
            }
        };



        /* Sets the correct opacity for face positions */

        Carousel.prototype._checkOpacity = function () {
            var face = Math.round( this._getFaceNumber( this._theta ) );

            var position = this._theta > 0 ? ( this._panelCount + 1 ) - face : face;
            var newPos = position === ( this._panelCount + 1 ) ? 1 : position;

            // hide out of sight faces
            var i = 0,
                len = this._element.children.length;

            for (; i < len; i++) {
                this._element.children[ i ].style[ 'opacity' ] = 0.1;
                this._element.children[ i ].className = 'background-panel';
            }

            var main = this._element.children[ newPos - 1 ];
            var leftSide = this._element.children[ newPos > this._panelCount - 1 ? 0 : newPos ];
            var rightSide = this._element.children[ newPos - 2 < 0 ? this._panelCount - 1 : newPos - 2 ];

            // change opacity for active panels
            main.style[ 'opacity' ] = 1;
            leftSide.style[ 'opacity' ] = 0.5;
            rightSide.style[ 'opacity' ] = 0.5;

            // add classes for custome styles
            main.className = 'main-panel';
            rightSide.className = 'right side-panel';
            leftSide.className = 'left side-panel';
        };



        /* Rotate wheel by increment value */

        /* Only use 1 or -1 */

        Carousel.prototype._rotateWheel = function ( value ) {
            var increment = parseInt(value);

            this._theta += ( 360 / this._panelCount ) * increment * -1;

            this._element.style[ 'transform' ] =
                                    'translateZ(-' + this._getTranslateZ() + 'px)' +
                                    'rotateY(' + this._theta + 'deg)';

            this._checkOpacity();
        };


        /* Drag carousel */

        Carousel.prototype._dragRotate = function ( distance ) {
            this._theta += distance*10;

            this._element.style[ transforms[ 'webkitTransform' ] ] =
                'translateZ(-' + this._getTranslateZ() + 'px)' +
                'rotateY(' + this._theta + 'deg)';
            this._element.style[ transforms[ 'MozTransform' ] ] =
                'translateZ(-' + this._getTranslateZ() + 'px)' +
                'rotateY(' + this._theta + 'deg)';

            this._checkOpacity();
        };


        /* Play Slide Show */

        Carousel.prototype.play = function () {
            var that = this;
            clearInterval( this._autoplayTimeout );
            this._autoplayTimeout = setInterval(function () {
                that._rotateWheel( 1 );
            }, this._settings.slideshowInterval);
        };


        /* Stop Slide Show */

        Carousel.prototype.stop = function () {
            clearInterval( this._autoplayTimeout );
        };

        window.mgrCarousel = Carousel;

    }( window ));

    var carousel = new mgrCarousel( 'carousel', { autoplay: true } );
    
});