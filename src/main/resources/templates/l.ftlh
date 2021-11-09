<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <title>另一边3D打印空间</title>
    <style>
        #nfcPwd::-webkit-input-placeholder {
            color: #aaa;
            font-size: 14px;
            /* text-align: center; */
            /* letter-spacing: 1px; */
        }
    </style>
</head>
<body
        style="background:url('${ImgUrl}') no-repeat center top;box-sizing: border-box;">
<div style="display: flex;justify-content: center;align-items: center;flex-direction: column; width:280px;height: 150px;margin: 60px auto 0;text-align: center;">
    <img style="height: 75px;width: 75px;border-radius: 50%;margin-bottom: 20px;"
         src="${companyBackground}" alt="">
    <div style="color: #fff; font-size: 16px;font-weight: bold;">${companySlogan}</div>
</div>
<!-- <div style="text-align: center;font-size: 24px;letter-spacing: 1px;">您的专属秘密</div> -->
<form role="form" action="/NFC/loginIn" onsubmit="return checkLength()" method="get"
      style="width: 280px;margin: 0 auto;">
    <input type="text" id="nfcUrl" name="nfcUrl" value="${url}" style="display:none;">
    <div style="display: flex;justify-content: center;align-items: center;align-content: center;">
        <input type="password" placeholder="请输入提取码" id="nfcPwd" name="nfcPwd"
               style="border-radius: 0;border:1px solid #fff; box-sizing: border-box;vertical-align:middle;width: 200px;height: 40px;background: #fff;border: none;">
        <button type="submit" id="login"
                style="box-sizing: content-box;vertical-align:middle; width: 80px;height: 42px;color: #fff;letter-spacing: 2px;font-size: 18px; background: rgba(13, 132, 243, 0.952);font-weight: 550;border: 0;">提取</button>
    </div>
    <div style="color: #444">
        ${NfcWord}
    </div>
</form>
<div style="position: fixed;bottom: 0px;left: 0; text-align: center; color: #fff; font-size: 14px;font-weight: bold;width: 100%;height: 50px">${corporateName}</div>
</body>
<script>
    function checkLength() {
        var a = document.getElementById("nfcPwd").value
        if (a == '') {
            console.log(a);
            alert('提取码不能为空');
            return false;
        } else {
            return true;
        }
    }
</script>
</html>