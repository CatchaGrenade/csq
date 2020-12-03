function pageLoaded() {
    let canvas = document.getElementById("canvas-id");
    let context = canvas.getContext("2d");
    let dpr = window.devicePixelRatio;
    let { width: cssWidth, height: cssHeight } = canvas.getBoundingClientRect();
    // 根据dpr，扩大canvas画布的像素，使1个canvas像素和1个物理像素相等
    canvas.width = dpr * cssWidth;
    canvas.height = dpr * cssHeight;
    // 由于画布扩大，canvas的坐标系也跟着扩大，如果按照原先的坐标系绘图内容会缩小，所以需要将绘制比例放大
    context.scale(dpr,dpr);
    //实心矩形
    context.fillRect(10, 10, 10, 10);
    context.fillRect(50, 70, 90, 30);
    //空心矩形
    context.strokeRect(110, 10, 50, 50);
    context.strokeRect(30, 10, 50, 50);
    //在给定矩形区域内清空矩形
    context.clearRect(10, 10, 10, 10);
    //画实心三角形
    context.beginPath();
    context.moveTo(10, 120);    // Start drawing at 10, 120
    context.lineTo(10, 180);
    context.lineTo(110, 150);
    context.fill();
    //画空心三角形
    context.beginPath();
    context.moveTo(140, 160); // Start drawing at 140, 160
    context.lineTo(140, 220);
    context.lineTo(40, 190);
    context.closePath();
    context.stroke();
    // 画不规则多边形图形
    context.beginPath();
    context.moveTo(160, 160); // Start drawing at 160, 160
    context.lineTo(170, 220);
    context.lineTo(240, 210);
    context.lineTo(260, 170);
    context.lineTo(190, 140);
    context.closePath();
    context.stroke();
    context.beginPath();
    //画个半圆
    context.arc(50, 300, 50, 0, Math.PI, true);
    context.stroke();
    context.beginPath();
    //画个圆
    context.arc(50, 300, 30, 0, 2 * Math.PI, true);
    context.fill();
    context.beginPath();
    //画3/4的⚪
    context.arc(200, 300, 25, 0, 3 / 2 * Math.PI, false);
    context.stroke();
    //文本
    context.fillText("This is some text...", 200, 40);
    context.font = "10pt Arial";
    context.fillText("This is in 10pt Arial...", 200, 60);
    context.font = "16pt Arial";
    context.strokeText("This is stroked in 16pt Arial...", 200, 80);
    //加颜色的矩形
    context.fillStyle = "red";
    context.fillRect(200, 80, 50, 50);
    //绿框的矩形
    context.strokeStyle = "green";
    context.strokeRect(250, 80, 50, 50);
    // Set fill color to yellow using rgb()
    context.fillStyle = "rgb(255, 255, 0)";
    context.fillRect(300, 80, 50, 50);
    //带透明度
    context.fillStyle = "rgba(0, 255, 0, 0.2)";
    context.fillRect(350, 80, 50, 50);
    //读取图片
    let image = new Image();
    image.src='http://localhost:9002/scms/xcpj/rest/xmfj/viewPic?url=/scms/kjmis/kjpjgl//2019/06/22/16b7c9dc81be7333f850ee94e6a9ee89/cfe37121cdb14a638e5632f535acd2f5&fjname=9469669_142840860000_2.jpg';
    image.onload = function(){
        context.drawImage(image, 0, 350, 100, 100);
    }

}