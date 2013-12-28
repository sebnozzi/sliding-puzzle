
function makeJsUIController() {

  var tiles = [];
  
  var imgWidth = undefined;
  var imgHeight = undefined;
  var tileWidth = undefined;
  var tileHeight = undefined;
  
  var tileCallback = function(tileId){};
  
  var jsUIController = {
    "imageLoaded": function() {
      var srcImg = $("#srcImg")[0];
      imgWidth = srcImg.width;
      imgHeight = srcImg.height;
    },
    "setupGrid": function(cols, rows) {

      $("#puzzle").empty();
      tiles = [];
    
      tileWidth = Math.floor(imgWidth / cols);
      tileHeight = Math.floor(imgHeight / rows);
      
      $("#puzzle").css("width", tileWidth * cols);
      $("#puzzle").css("height", tileHeight * rows);
      
      for(var row = 0; row < rows; row++) {
        for(var col = 0; col < cols; col++) {
          var id = "canvas_"+col+"_"+row;
          var tile = $('<canvas id="'+id+'" data-col="'+col+'" data-row="'+row+
            '" class="absolute" width="'+tileWidth+'" height="'+tileHeight+'"/>');
    
          $("#puzzle").append(tile);
          tile = tile.get(0);
    
          var img = $("#srcImg").get(0);
          var ctx = tile.getContext("2d");
          var sx = col * tileWidth;
          var sy = row * tileHeight;
          ctx.drawImage(img, sx, sy, tileWidth, tileHeight, 0, 0, tileWidth, tileHeight);
          ctx.strokeRect(0, 0, tileWidth, tileHeight);
          
          $(tile).css("left", sx);
          $(tile).css("top", sy);
          
          tiles.push(tile);
        }
      }
    
      var _thisClosure = this;
      $(tiles).click(function() {
        var col = parseInt($(this).attr("data-col"));
        var row = parseInt($(this).attr("data-row"));
        var tileId = $(this).attr("id");
        _thisClosure.tileClicked(tileId);
      });
    
    },
    "tileClicked": function(tileId) {
      tileCallback(tileId);
    },    
    "findTile": function(id) {
      for(var i = 0; i < tiles.length; i++) {
        if($(tiles[i]).attr("id") == id)
          return tiles[i];
      }
      return undefined;
    },    
    "getTileWidth": function() {
      return tileWidth;
    },
    "getTileHeight": function() {
      return tileHeight;
    },
    "getTileIds": function() {
      var ids = [];
      for(var i=0; i < tiles.length; i++) {
        ids.push($(tiles[i]).attr("id"));
      }
      return ids;
    },
    "onTileClicked": function(callback) {
      tileCallback = callback;
    }    
  };
  
  return jsUIController;
}

  


  

