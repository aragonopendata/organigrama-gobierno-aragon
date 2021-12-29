// ESTOS SON JS PARA EL DISENO
var mainApp = (function(){

  // Funcionamiento botones de Zoom
  var increaseFontSize = function(){

    function changeFont(fontSize) {
      return function() {
          $('html').css('font-size', fontSize + '%');
          sessionStorage.setItem('fSize', fontSize);
      }
    }

    var normalFont = changeFont(62.5),
        littleFont = changeFont(48.625),
        largeFont  = changeFont(125);
        
    // gestion click  botones al decrecer fuente
    $('.zoom__neg').on('click', function(){
        $('body').removeClass('zoom-in');
        $('.zoom__pos a').attr('aria-disabled','false');
        if($('.zoom__pos').hasClass('active')){
          normalFont();
          $('.zoom > li').removeClass('active');
          $(this).find('a').attr('aria-disabled','false');
          $('.dga-view header .navbar-collapse .language').addClass('hidden-sm').addClass('hidden-md').addClass('hidden-lg').removeClass('show');
        }
        else{
          littleFont();
          $(this).addClass('active');
          $('body').addClass('zoom-out');
          $(this).find('a').attr('aria-disabled','true');
        }
        stickyBar();
    });
    // gestion teclado  botones al decrecer fuente
    $('.zoom__neg').keydown(function(e) {
        $('body').removeClass('zoom-in');
        $('.zoom__pos a').attr('aria-disabled','false');
        if(e.which == 13) {
          if($('.zoom__pos').hasClass('active')){
            normalFont();
            $('.zoom > li').removeClass('active');
            $(this).find('a').attr('aria-disabled','false');
            $('.dga-view header .navbar-collapse .language').addClass('hidden-sm').addClass('hidden-md').addClass('hidden-lg').removeClass('show');
          }
          else{
            littleFont();
            $(this).addClass('active');
            $('body').addClass('zoom-out');
            $(this).find('a').attr('aria-disabled','true');
          }
        }
        stickyBar();
    });
    // gestion click  botones al aumentar fuente
    $('.zoom__pos').on('click', function(){
        $('body').removeClass('zoom-out');
        $('.zoom__neg a').attr('aria-disabled','false');
        if($('.zoom__neg').hasClass('active')){
          normalFont();
          $('.zoom > li').removeClass('active');
          $(this).find('a').attr('aria-disabled','false');
        }
        else{
          largeFont();
          $(this).addClass('active');
          $('body').addClass('zoom-in');
          $('.dga-view header .navbar-collapse .language').removeClass('hidden-sm').removeClass('hidden-md').removeClass('hidden-lg').addClass('show');
          $(this).find('a').attr('aria-disabled','true');
        }
        stickyBar();
    });
    // gestion teclado  botones al aumentar fuente
    $('.zoom__pos').keydown(function(e) {
        $('body').removeClass('zoom-out');
        $('.zoom__neg a').attr('aria-disabled','false');
        if(e.which == 13) {
          if($('.zoom__neg').hasClass('active')){
              normalFont();
              $('.zoom > li').removeClass('active');
              $(this).find('a').attr('aria-disabled','false');
          }
          else{
            largeFont();
            $(this).addClass('active');
            $('body').addClass('zoom-in');
            $('.dga-view header .navbar-collapse .language').removeClass('hidden-sm').removeClass('hidden-md').removeClass('hidden-lg').addClass('show');
            $(this).find('a').attr('aria-disabled','true');
          }
        }
        stickyBar();
    });
  }
	
  return{
    increaseFontSize : function(){
      increaseFontSize();
    }
  }
})();

$(document).ready(function() {
    // On load
    mainApp.increaseFontSize();
});
