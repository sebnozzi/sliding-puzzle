function makeJsUIController() {

  var tiles = [];

  var jsUIController = {
    "setupGrid" : function(srcImg, target, cols, rows) {

      var imgWidth = srcImg.width;
      var imgHeight = srcImg.height;      
      var tileWidth = Math.floor(imgWidth / cols);
      var tileHeight = Math.floor(imgHeight / rows);
      
      tiles = [];

      for (var row = 0; row < rows; row++) {
        for (var col = 0; col < cols; col++) {
          var id = "canvas_" + col + "_" + row;
          var tile = $('<canvas id="' + id + '" ' + 
              'class="absolute" width="' + tileWidth + '" '+
              'height="' + tileHeight + '"/>');

          $(target).append(tile);
          tile = tile.get(0);

          var ctx = tile.getContext("2d");
          var sx = col * tileWidth;
          var sy = row * tileHeight;
          ctx.drawImage(srcImg, sx, sy, tileWidth, tileHeight, 0, 0, tileWidth,
              tileHeight);
          ctx.strokeRect(0, 0, tileWidth, tileHeight);

          $(tile).css("left", sx);
          $(tile).css("top", sy);

          tiles.push(tile);
        }
      }
    },
    "getNativeTiles" : function() {
      return tiles;
    }
  };

  return jsUIController;
}
