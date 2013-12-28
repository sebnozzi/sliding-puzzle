function makeJsUIController() {

  var tiles = [];

  var jsUIController = {
    "setupGrid" : function(cols, rows) {

      var srcImg = $("#srcImg")[0];
      var imgWidth = srcImg.width;
      var imgHeight = srcImg.height;      
      var tileWidth = Math.floor(imgWidth / cols);
      var tileHeight = Math.floor(imgHeight / rows);
      
      $("#puzzle").empty();
      tiles = [];

      for (var row = 0; row < rows; row++) {
        for (var col = 0; col < cols; col++) {
          var id = "canvas_" + col + "_" + row;
          var tile = $('<canvas id="' + id + '" ' + 
              'class="absolute" width="' + tileWidth + '" '+
              'height="' + tileHeight + '"/>');

          $("#puzzle").append(tile);
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
    "findTile" : function(id) {
      for (var i = 0; i < tiles.length; i++) {
        if ($(tiles[i]).attr("id") == id)
          return tiles[i];
      }
      return undefined;
    },
    "getTileIds" : function() {
      var ids = [];
      for (var i = 0; i < tiles.length; i++) {
        ids.push($(tiles[i]).attr("id"));
      }
      return ids;
    }
  };

  return jsUIController;
}
