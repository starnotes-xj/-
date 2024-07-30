package JFrameUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class CourseJFrame extends JFrame {
    static String username;
    static String password;
    static String url;
    static JFrame course;
    static JLabel usernameLabel, passwordLabel, urlLabel;
    static JTextField usernameTextField, passwordTextField, urlTextField;
    static JButton button;
    static String useInstructions = "在使用本软件之前请仔细阅读以下使用说明：\n" + "输入账号，密码，网课链接之后选择自己需要抢的课程，再点击抢课";
    static List<String> courseList;
    static JFrame chooseCourse;
    static String iconPath = "src/main/resources/frame.jpg";
    public static void main(String[] args) {
        // 初始化界面
        // 创建一个JFrame窗口，标题为“抢课程序”
        course = new JFrame("抢课程序");
        // 设置关闭操作
        course.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置窗口位置,居中显示
        course.setLocationRelativeTo(null);
        // 设置窗口大小
        course.setSize(800, 500);
        // 创建一个用户名标签
        usernameLabel = new JLabel("用户名:");
        // 创建一个密码标签
        passwordLabel = new JLabel("密码:");
        // 创建一个课程链接标签
        urlLabel = new JLabel("课程链接:");
        // 创建一个用户名文本框
        usernameTextField = new JTextField(20);
        // 创建一个密码文本框
        passwordTextField = new JTextField(20);
        // 创建一个课程链接文本框
        urlTextField = new JTextField(100);
        // 创建一个开始抢课按钮
        button = new JButton("开始抢课");
        // 设置用户名标签对应文本框
        usernameLabel.setLabelFor(usernameTextField);
        // 设置密码标签对应文本框
        passwordLabel.setLabelFor(passwordTextField);
        // 设置课程链接标签对应文本框
        urlLabel.setLabelFor(urlTextField);
        // 获取用户名文本框的内容
        usernameTextField.addActionListener(e -> {
            username = usernameTextField.getText();
            passwordTextField.requestFocus();
        });
        // 获取密码文本框的内容
        passwordTextField.addActionListener(e -> {
            password = passwordTextField.getText();
            urlTextField.requestFocus();
        });
        // 获取课程链接文本框的内容
        urlTextField.addActionListener(e -> {
            url = urlTextField.getText();
            button.requestFocus();
        });
        button.setMnemonic(KeyEvent.VK_ENTER);
        button.addActionListener(e -> {
            try {
                // 判断是否输入完整信息
                if (usernameTextField.getText() == "" || passwordTextField.getText() == "" || urlTextField.getText() == "") {
                    JOptionPane.showMessageDialog(null, "请输入完整信息", "提示", JOptionPane.WARNING_MESSAGE);
                    // 判断哪个文本框为空，将焦点移到该文本框
                    if (usernameTextField.getText() == null) {
                        usernameTextField.requestFocus();
                    } else if (passwordTextField.getText() == null) {
                        passwordTextField.requestFocus();
                    } else {
                        urlTextField.requestFocus();
                    }
                }
                // 如果输入完整信息，则调用 CourseUtil 类的 start() 方法
                if (usernameTextField.getText() != null && passwordTextField.getText() != "" && urlTextField.getText() != "") {
                    username = usernameTextField.getText();
                    password = passwordTextField.getText();
                    url = urlTextField.getText();
                    new CourseUtil(username, password, url).start();
                }

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        // 创建一个ImageIcon对象，加载图片资源
        ImageIcon imageIcon = new ImageIcon(iconPath);
        // 设置课程图标
        course.setIconImage(imageIcon.getImage());
        // 创建一个菜单栏
        JMenuBar menuBar = new JMenuBar();
        // 创建一个设置菜单
        JMenu seting = new JMenu("设置");
        // 创建一个帮助菜单
        JMenu help = new JMenu("帮助");
        // 创建一个必修课菜单
        JMenu requiredCourse = new JMenu("必修课");
        // 创建一个专业选修课菜单
        JMenu professionalElectiveCourses = new JMenu("专业选修课");
        // 创建一个通识选修课菜单
        JMenu generalElectiveCourses = new JMenu("通识选修课");
        // 创建一个素质拓展课程菜单
        JMenu qualityExpansionCourse = new JMenu("素质拓展课程");
        // 创建一个班级课程菜单
        JMenu classCourses = new JMenu("班级课程");
        // 创建一个跨专业课程菜单
        JMenu interdisciplinaryCourseSelection = new JMenu("跨专业课程");
        // 创建一个使用说明菜单项
        JMenuItem instruction = new JMenuItem("使用说明");
        // 创建一个联系作者菜单项
        JMenuItem contactAuthor = new JMenuItem();
        //创建一个JMenuItem对象，用于选择课程
        JMenuItem setCourse = new JMenuItem("设置课程列表");
        setCourse.addActionListener(e -> chooseCourse());
        seting.add(setCourse);
        // 为联系作者菜单项添加事件监听器
        contactAuthor.addActionListener(e -> JOptionPane.showMessageDialog(null, "作者：星记\n邮箱：starnotes@qq.com", "联系作者", JOptionPane.INFORMATION_MESSAGE));
        // 为使用说明菜单项添加事件监听器
        instruction.addActionListener(e -> JOptionPane.showMessageDialog(null, useInstructions, "使用说明", JOptionPane.INFORMATION_MESSAGE));
        // 将使用说明和联系作者菜单项添加到帮助菜单中
        help.add(instruction);
        help.add(contactAuthor);
        menuBar.add(seting);
        // 将帮助菜单添加到菜单栏中
        menuBar.add(help);
        // 将必修课、专业选修课、通识选修课、素质拓展课程、班级课程和跨专业课程菜单添加到菜单栏中
        menuBar.add(requiredCourse);
        menuBar.add(professionalElectiveCourses);
        menuBar.add(generalElectiveCourses);
        menuBar.add(qualityExpansionCourse);
        menuBar.add(classCourses);
        menuBar.add(interdisciplinaryCourseSelection);
        // 设置课程的菜单栏
        course.setJMenuBar(menuBar);
        // 创建一个面板，并设置布局为网格布局
        JPanel panel = new JPanel();
        //设置panel的布局为GridLayout，4行1列
        panel.setLayout(new GridLayout(4, 1));
        //向panel中添加usernameLabel
        panel.add(usernameLabel);
        //向panel中添加usernameTextField
        panel.add(usernameTextField);
        //向panel中添加passwordLabel
        panel.add(passwordLabel);
        //向panel中添加passwordTextField
        panel.add(passwordTextField);
        //向panel中添加urlLabel
        panel.add(urlLabel);
        //向panel中添加urlTextField
        panel.add(urlTextField);
        //向panel中添加button
        panel.add(button);
        //向course中添加panel
        course.add(panel);
        //方法用于使窗口可见
        course.setVisible(true);


    }
    public static void chooseCourse() {
        chooseCourse = new JFrame("选择课程");
        chooseCourse.setSize(400, 300);
        chooseCourse.setLocationRelativeTo(null);
        chooseCourse.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon imageIcon = new ImageIcon(iconPath);
        chooseCourse.setIconImage(imageIcon.getImage());
        JList coursesList = new JList();
        JPanel  panel = new JPanel();
        panel.add(coursesList);
         // 将滚动面板添加到窗口中
         chooseCourse.add(panel);
         // 设置窗口可见
        chooseCourse.setVisible(true);
    }
}
