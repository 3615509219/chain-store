<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport"
    content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <title>success</title>
    <style >
    .box {
    width:  375px;
    height: 240px;
    /*border:  1px solid red;*/
    overflow:  hidden;
    margin-bottom:  20px;
    display: flex;
    justify-content: center;
    align-content: center;
    align-items: center;
    }
    /* 小方平电脑高度铺满，宽高比例 */
    </style >
</head>
<body style="overflow-x: hidden">
<#--    <div style="width: 100%;height: 100%;overflow-x: hidden;">-->
<#--        <embed  style="width: 100%;height: 100%;overflow-x: hidden;" width="280" height="200" align="center" hidden=true>-->
<#--    </div>-->
    <div  class= "box">
    <embed  width= "90%"  height= "100%"  quality= "high"  src="${nfc}"  align= "middle"  allowscriptaccess= "never" allowfullscreeninteractive="true"  allowfullscreen= "true"  mode= "transparent"  wmode= "transparent" palette="black">
    </div>
</body>
</html>