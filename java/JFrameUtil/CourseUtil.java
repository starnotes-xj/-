package JFrameUtil;

import BaiDuAPI.ImageRecognition;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class CourseUtil {
    private static final String key = "webdriver.edge.driver";
    private static final String value = "D:\\Java\\jdk-22\\msedgedriver.exe";
    private static final String SAVEPATH = "D:\\VerificationCodeImage\\";
    private static String username = "";
    private static String password = "";
    private static String url = "";
    public CourseUtil(String username, String password, String url) throws Exception {
        CourseUtil.username = username;
        CourseUtil.password = password;
        CourseUtil.url = url;
    }
    public void start() throws Exception {
        //设置系统属性
        System.setProperty(key, value);
        //创建EdgeOptions对象
        EdgeOptions options = new EdgeOptions();
        //添加参数
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");
        //创建EdgeDriver对象
        EdgeDriver driver = new EdgeDriver(options);
        //打开网页
        driver.get(url);
        //找到账号输入框
        WebElement accountInput = driver.findElement(By.id("account"));
        //找到密码输入框
        WebElement passwordInput = driver.findElement(By.id("password"));
        //找到验证码图片
        WebElement captchaImage = driver.findElement(By.id("imgCode"));
        //输入账号
        accountInput.sendKeys(username);
        //输入密码
        passwordInput.sendKeys(password);
        //创建ImageRecognition对象
        ImageRecognition imageRecognition = new ImageRecognition();
        //获取验证码的截图
        //获取验证码图片的位置
        Point captchaLocation = captchaImage.getLocation();
        //获取验证码图片的大小
        Dimension captchaSize = captchaImage.getSize();
        //计算验证码图片的左边界
        int left = (int) (captchaLocation.getX() * 1.5);
        //计算验证码图片的上边界
        int top = (int) (captchaLocation.getY() * 1.5);
        //计算验证码图片的右边界
        int right = (int) (left + captchaSize.getWidth() * 1.5);
        //计算验证码图片的下边界
        int bottom = (int) (top + captchaSize.getHeight() * 1.5);
        //获取屏幕截图
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //读取截图中的图片
        BufferedImage captchaTemp = ImageIO.read(screenshot);
        //截取验证码图片
        BufferedImage captcha = captchaTemp.getSubimage(left, top, right - left, bottom - top);
        //创建输出目录
        File outputDir = new File(SAVEPATH);
        //将验证码图片写入文件
        ImageIO.write(captcha, "jpg", new File(outputDir, "captcha.jpg"));
        //识别验证码
        String code = imageRecognition.imageRecongintion(SAVEPATH + "captcha.jpg");
        //找到验证码输入框
        WebElement captchaInput = driver.findElement(By.id("captcha"));
        //输入验证码
        captchaInput.sendKeys(code);
        //找到登录按钮
        WebElement loginButton = driver.findElement(By.id("pwdBtn"));
        //点击登录按钮
        loginButton.click();
    }

}

