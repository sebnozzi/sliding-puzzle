$(document).ready(function() {
    
  window.jsUIController = makeJsUIController();
  var jsUIController = window.jsUIController;
    
  $("#sizeSelector").on("change", function() {
    jsUIController.onSizeChanged();
  });

  $("#shuffleButton").on("click", function() {
    jsUIController.onShufflePressed();
  });

  $("#resetButton").on("click", function() {
    jsUIController.onResetPressed();
  });

  jsUIController.onImageLoaded();

  jsUIController.setupGrid(4,3);
    
  ScalaJS.modules.com_sebnozzi_slidingpuzzle_SlidingPuzzleMain().main();
  
});

