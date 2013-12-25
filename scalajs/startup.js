
$(document)["ready"](function() {
  
  window["jsUIController"] = makeJsUIController();
  var jsUIController = window["jsUIController"];
  
  var imgSrc = $("#imgAnchor")["attr"]("href");
  var tmpImg = new Image();
  tmpImg["src"] = imgSrc;
  tmpImg["onload"] = function() {
    $("#hiddenContainer")["append"](tmpImg);
    $(tmpImg)["attr"]("id", "srcImg");
    
    jsUIController["imageLoaded"]();
    
    ScalaJS.modules.com_sebnozzi_slidingpuzzle_SlidingPuzzleMain().main();
  };
  
});

