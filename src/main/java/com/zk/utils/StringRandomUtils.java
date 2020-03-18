package com.zk.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings({"ALL", "AlibabaAvoidNewDateGetTime"})
public class StringRandomUtils {
    public static String base = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static String firstName = "赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅皮卞齐康伍余元卜顾孟平黄和穆萧尹姚邵湛汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞熊纪舒屈项祝董梁杜阮蓝闵席季麻强贾路娄危江童颜郭梅盛林刁钟徐邱骆高夏蔡田樊胡凌霍虞万支柯咎管卢莫经房裘缪干解应宗宣丁贲邓郁单杭洪包诸左石崔吉钮龚程嵇邢滑裴陆荣翁荀羊於惠甄魏加封芮羿储靳汲邴糜松井段富巫乌焦巴弓牧隗山谷车侯宓蓬全郗班仰秋仲伊宫宁仇栾暴甘钭厉戎祖武符刘姜詹束龙叶幸司韶郜黎蓟薄印宿白怀蒲台从鄂索咸籍赖卓蔺屠蒙池乔阴郁胥能苍双闻莘党翟谭贡劳逄姬申扶堵冉宰郦雍却璩桑桂濮牛寿通边扈燕冀郏浦尚农温别庄晏柴瞿阎充慕连茹习宦艾鱼容向古易慎戈廖庚终暨居衡步都耿满弘匡国文寇广禄阙东殴殳沃利蔚越夔隆师巩厍聂晁勾敖融冷訾辛阚那简饶空曾毋沙乜养鞠须丰巢关蒯相查后江红游竺权逯盖益桓公万俟司马上官欧阳夏侯诸葛闻人东方赫连皇甫尉迟公羊澹台公冶宗政濮阳淳于仲孙太叔申屠公孙乐正轩辕令狐钟离闾丘长孙慕容鲜于宇文司徒司空亓官司寇仉督子车颛孙端木巫马公西漆雕乐正壤驷公良拓拔夹谷宰父谷粱晋楚阎法汝鄢涂钦段干百里东郭南门呼延归海羊舌微生岳帅缑亢况后有琴梁丘左丘东门西门商牟佘佴伯赏南宫墨哈谯笪年爱阳佟第五言福百家姓续";
    private static String girl = "秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽 ";
    private static String boy = "伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘";
    private static String[] road = "重庆大厦,黑龙江路,十梅庵街,遵义路,湘潭街,瑞金广场,仙山街,仙山东路,仙山西大厦,白沙河路,赵红广场,机场路,民航街,长城南路,流亭立交桥,虹桥广场,长城大厦,礼阳路,风岗街,中川路,白塔广场,兴阳路,文阳街,绣城路,河城大厦,锦城广场,崇阳街,华城路,康城街,正阳路,和阳广场,中城路,江城大厦,顺城路,安城街,山城广场,春城街,国城路,泰城街,德阳路,明阳大厦,春阳路,艳阳街,秋阳路,硕阳街,青威高速,瑞阳街,丰海路,双元大厦,惜福镇街道,夏庄街道,古庙工业园,中山街,太平路,广西街,潍县广场,博山大厦,湖南路,济宁街,芝罘路,易州广场,荷泽四路,荷泽二街,荷泽一路,荷泽三大厦,观海二广场,广西支街,观海一路,济宁支街,莒县路,平度广场,明水路,蒙阴大厦,青岛路,湖北街,江宁广场,郯城街,天津路,保定街,安徽路,河北大厦,黄岛路,北京街,莘县路,济南街,宁阳广场,日照街,德县路,新泰大厦,荷泽路,山西广场,沂水路,肥城街,兰山路,四方街,平原广场,泗水大厦,浙江路,曲阜街,寿康路,河南广场,泰安路,大沽街,红山峡支路,西陵峡一大厦,台西纬一广场,台西纬四街,台西纬二路,西陵峡二街,西陵峡三路,台西纬三广场,台西纬五路,明月峡大厦,青铜峡路,台西二街,观音峡广场,瞿塘峡街,团岛二路,团岛一街,台西三路,台西一大厦,郓城南路,团岛三街,刘家峡路,西藏二街,西藏一广场,台西四街,三门峡路,城武支大厦,红山峡路,郓城北广场,龙羊峡路,西陵峡街,台西五路,团岛四街,石村广场,巫峡大厦,四川路,寿张街,嘉祥路,南村广场,范县路,西康街,云南路,巨野大厦,西江广场,鱼台街,单县路,定陶街,滕县路,钜野广场,观城路,汶上大厦,朝城路,滋阳街,邹县广场,濮县街,磁山路,汶水街,西藏路,城武大厦,团岛路,南阳街,广州路,东平街,枣庄广场,贵州街,费县路,南海大厦,登州路,文登广场,信号山支路,延安一街,信号山路,兴安支街,福山支广场,红岛支大厦,莱芜二路,吴县一街,金口三路,金口一广场,伏龙山路,鱼山支街,观象二路,吴县二大厦,莱芜一广场,金口二街,海阳路,龙口街,恒山路,鱼山广场,掖县路,福山大厦,红岛路,常州街,大学广场,龙华街,齐河路,莱阳街,黄县路,张店大厦,祚山路,苏州街,华山路,伏龙街,江苏广场,龙江街,王村路,琴屿大厦,齐东路,京山广场,龙山路,牟平街,延安三路,延吉街,南京广场,东海东大厦,银川西路,海口街,山东路,绍兴广场,芝泉路,东海中街,宁夏路,香港西大厦,隆德广场,扬州街,郧阳路,太平角一街,宁国二支路,太平角二广场,天台东一路,太平角三大厦,漳州路一路,漳州街二街,宁国一支广场,太平角六街,太平角四路,天台东二街,太平角五路,宁国三大厦,澳门三路,江西支街,澳门二路,宁国四街,大尧一广场,咸阳支街,洪泽湖路,吴兴二大厦,澄海三路,天台一广场,新湛二路,三明北街,新湛支路,湛山五街,泰州三广场,湛山四大厦,闽江三路,澳门四街,南海支路,吴兴三广场,三明南路,湛山二街,二轻新村镇,江南大厦,吴兴一广场,珠海二街,嘉峪关路,高邮湖街,湛山三路,澳门六广场,泰州二路,东海一大厦,天台二路,微山湖街,洞庭湖广场,珠海支街,福州南路,澄海二街,泰州四路,香港中大厦,澳门五路,新湛三街,澳门一路,正阳关街,宁武关广场,闽江四街,新湛一路,宁国一大厦,王家麦岛,澳门七广场,泰州一路,泰州六街,大尧二路,青大一街,闽江二广场,闽江一大厦,屏东支路,湛山一街,东海西路,徐家麦岛函谷关广场,大尧三路,晓望支街,秀湛二路,逍遥三大厦,澳门九广场,泰州五街,澄海一路,澳门八街,福州北路,珠海一广场,宁国二路,临淮关大厦,燕儿岛路,紫荆关街,武胜关广场,逍遥一街,秀湛四路,居庸关街,山海关路,鄱阳湖大厦,新湛路,漳州街,仙游路,花莲街,乐清广场,巢湖街,台南路,吴兴大厦,新田路,福清广场,澄海路,莆田街,海游路,镇江街,石岛广场,宜兴大厦,三明路,仰口街,沛县路,漳浦广场,大麦岛,台湾街,天台路,金湖大厦,高雄广场,海江街,岳阳路,善化街,荣成路,澳门广场,武昌路,闽江大厦,台北路,龙岩街,咸阳广场,宁德街,龙泉路,丽水街,海川路,彰化大厦,金田路,泰州街,太湖路,江西街,泰兴广场,青大街,金门路,南通大厦,旌德路,汇泉广场,宁国路,泉州街,如东路,奉化街,鹊山广场,莲岛大厦,华严路,嘉义街,古田路,南平广场,秀湛路,长汀街,湛山路,徐州大厦,丰县广场,汕头街,新竹路,黄海街,安庆路,基隆广场,韶关路,云霄大厦,新安路,仙居街,屏东广场,晓望街,海门路,珠海街,上杭路,永嘉大厦,漳平路,盐城街,新浦路,新昌街,高田广场,市场三街,金乡东路,市场二大厦,上海支路,李村支广场,惠民南路,市场纬街,长安南路,陵县支街,冠县支广场,小港一大厦,市场一路,小港二街,清平路,广东广场,新疆路,博平街,港通路,小港沿,福建广场,高唐街,茌平路,港青街,高密路,阳谷广场,平阴路,夏津大厦,邱县路,渤海街,恩县广场,旅顺街,堂邑路,李村街,即墨路,港华大厦,港环路,馆陶街,普集路,朝阳街,甘肃广场,港夏街,港联路,陵县大厦,上海路,宝山广场,武定路,长清街,长安路,惠民街,武城广场,聊城大厦,海泊路,沧口街,宁波路,胶州广场,莱州路,招远街,冠县路,六码头,金乡广场,禹城街,临清路,东阿街,吴淞路,大港沿,辽宁路,棣纬二大厦,大港纬一路,贮水山支街,无棣纬一广场,大港纬三街,大港纬五路,大港纬四街,大港纬二路,无棣二大厦,吉林支路,大港四街,普集支路,无棣三街,黄台支广场,大港三街,无棣一路,贮水山大厦,泰山支路,大港一广场,无棣四路,大连支街,大港二路,锦州支街,德平广场,高苑大厦,长山路,乐陵街,临邑路,嫩江广场,合江路,大连街,博兴路,蒲台大厦,黄台广场,城阳街,临淄路,安邱街,临朐路,青城广场,商河路,热河大厦,济阳路,承德街,淄川广场,辽北街,阳信路,益都街,松江路,流亭大厦,吉林路,恒台街,包头路,无棣街,铁山广场,锦州街,桓台路,兴安大厦,邹平路,胶东广场,章丘路,丹东街,华阳路,青海街,泰山广场,周村大厦,四平路,台东西七街,台东东二路,台东东七广场,台东西二路,东五街,云门二路,芙蓉山村,延安二广场,云门一街,台东四路,台东一街,台东二路,杭州支广场,内蒙古路,台东七大厦,台东六路,广饶支街,台东八广场,台东三街,四平支路,郭口东街,青海支路,沈阳支大厦,菜市二路,菜市一街,北仲三路,瑞云街,滨县广场,庆祥街,万寿路,大成大厦,芙蓉路,历城广场,大名路,昌平街,平定路,长兴街,浦口广场,诸城大厦,和兴路,德盛街,宁海路,威海广场,东山路,清和街,姜沟路,雒口大厦,松山广场,长春街,昆明路,顺兴街,利津路,阳明广场,人和路,郭口大厦,营口路,昌邑街,孟庄广场,丰盛街,埕口路,丹阳街,汉口路,洮南大厦,桑梓路,沾化街,山口路,沈阳街,南口广场,振兴街,通化路,福寺大厦,峄县路,寿光广场,曹县路,昌乐街,道口路,南九水街,台湛广场,东光大厦,驼峰路,太平山,标山路,云溪广场,太清路".split(",");
    private static final String[] email_suffix = "@gmail.com,@yahoo.com,@msn.com,@hotmail.com,@aol.com,@ask.com,@live.com,@qq.com,@0355.net,@163.com,@163.net,@263.net,@3721.net,@yeah.net,@googlemail.com,@126.com,@sina.com,@sohu.com,@yahoo.com.cn".split(",");
    private static Random r = new Random();

    public static int getNum(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }

    /**
     * 获取随机生成的身份证号码
     *
     * @return
     * @author mingzijian
     */
    public static String getRandomID() {
        String id = "420222199204179999";
        // 随机生成省、自治区、直辖市代码 1-2
        String provinces[] = {"11", "12", "13", "14", "15", "21", "22", "23",
                "31", "32", "33", "34", "35", "36", "37", "41", "42", "43",
                "44", "45", "46", "50", "51", "52", "53", "54", "61", "62",
                "63", "64", "65", "71", "81", "82"};
        String province = randomOne(provinces);
        // 随机生成地级市、盟、自治州代码 3-4
        String city = randomCityCode(18);
        // 随机生成县、县级市、区代码 5-6
        String county = randomCityCode(28);
        // 随机生成出生年月 7-14
        String birth = randomBirthSFZ(20, 50);
        // 随机生成顺序号 15-17(随机性别)
        String no = new Random().nextInt(899) + 100 + "";
        // 随机生成校验码 18
        String checks[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "X"};
        String check = randomOne(checks);
        // 拼接身份证号码
        id = province + city + county + birth + no + check;
        return id;
    }


    /**
     * 从String[] 数组中随机取出其中一个String字符串
     *
     * @param s
     * @return
     * @author mingzijian
     */
    public static String randomOne(String s[]) {
        return s[new Random().nextInt(s.length - 1)];
    }


    /**
     * 随机生成两位数的字符串（01-max）,不足两位的前面补0
     *
     * @param max
     * @return
     * @author mingzijian
     */
    public static String randomCityCode(int max) {
        int i = new Random().nextInt(max) + 1;
        return i > 9 ? i + "" : "0" + i;
    }


    /**
     * 随机生成minAge到maxAge年龄段的人的生日日期
     *
     * @param minAge
     * @param maxAge
     * @return
     * @author mingzijian
     */
    public static String randomBirth(int minAge, int maxAge) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy.MM");// 设置日期格式
        Calendar date = Calendar.getInstance();
        date.setTime(new Date());// 设置当前日期
        // 随机设置日期为前maxAge年到前minAge年的任意一天
        int randomDay = 365 * minAge
                + new Random().nextInt(365 * (maxAge - minAge));
        date.set(Calendar.DATE, date.get(Calendar.DATE) - randomDay);
        return dft.format(date.getTime());
    }

    public static String randomBirth2(int minAge, int maxAge) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy/MM");// 设置日期格式
        Calendar date = Calendar.getInstance();
        date.setTime(new Date());// 设置当前日期
        // 随机设置日期为前maxAge年到前minAge年的任意一天
        int randomDay = 365 * minAge
                + new Random().nextInt(365 * (maxAge - minAge));
        date.set(Calendar.DATE, date.get(Calendar.DATE) - randomDay);
        return dft.format(date.getTime());
    }

    public static String randomBirthSFZ(int minAge, int maxAge) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");// 设置日期格式
        Calendar date = Calendar.getInstance();
        date.setTime(new Date());// 设置当前日期
        // 随机设置日期为前maxAge年到前minAge年的任意一天
        int randomDay = 365 * minAge
                + new Random().nextInt(365 * (maxAge - minAge));
        date.set(Calendar.DATE, date.get(Calendar.DATE) - randomDay);
        return dft.format(date.getTime());
    }


    /**
     * 返回Email
     *
     * @param lMin 最小长度
     * @param lMax 最大长度
     * @return
     */
    public static String getEmail(int lMin, int lMax) {
        int length = getNum(lMin, lMax);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = (int) (Math.random() * base.length());
            sb.append(base.charAt(number));
        }
        sb.append(email_suffix[(int) (Math.random() * email_suffix.length)]);
        return sb.toString();
    }

    /**
     * 返回手机号码
     */
    public static String[] telFirst = "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");

    public static String getTel() {
        int index = getNum(0, telFirst.length - 1);
        String first = telFirst[index];
        String second = String.valueOf(getNum(1, 888) + 10000).substring(1);
        String thrid = String.valueOf(getNum(1, 9100) + 10000).substring(1);
        return first + second + thrid;
    }

    /**
     * 返回中文姓名
     */
    private static String name_sex = "";

    public static String getChineseName() {
        int index = getNum(0, firstName.length() - 1);
        String first = firstName.substring(index, index + 1);
        int sex = getNum(0, 1);
        String str = boy;
        int length = boy.length();
        if (sex == 0) {
            str = girl;
            length = girl.length();
            name_sex = "女";
        } else {
            name_sex = "男";
        }
        index = getNum(0, length - 1);
        String second = str.substring(index, index + 1);
        int hasThird = getNum(0, 1);
        String third = "";
        if (hasThird == 1) {
            index = getNum(0, length - 1);
            third = str.substring(index, index + 1);
        }
        return first + second + third;
    }

    /**
     * 返回地址
     *
     * @return
     */
    public static String getRoad() {
        int index = getNum(0, road.length - 1);
        String first = road[index];
        String second = String.valueOf(getNum(11, 150)) + "号";
        String third = "-" + getNum(1, 20) + "-" + getNum(1, 10);
        return first + second + third;
    }

    /**
     * 数据封装
     *
     * @return
     */
    public static Map getAddress() {
        Map map = new HashMap();
        map.put("name", getChineseName());
        map.put("sex", name_sex);
        map.put("road", getRoad());
        map.put("tel", getTel());
        map.put("email", getEmail(6, 9));
        return map;
    }

    public static String getName() {
        return getChineseName();
    }


    /*
     * 解析XML
     */
    public static void read(String fileName) throws Exception {
        // 定义工厂API 使应用程序能够从XML文档获取生成DOM对象树的解析器
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String s = "";
        String body = "";

        while ((s = br.readLine()) != null) {
            body += s;
        }
        String reg = "<.*?>";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(body);
        List<String> list = new ArrayList();
        while (m.find()) {
//            System.out.println(m.group());
            body = body.replace(m.group(), "");


        }


        System.out.println(body.replaceAll("[\\w/+=:-]{20,}?", ""));

    }

    public static String getRandomCode(int passLength, int type) {
        StringBuffer buffer = null;
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        switch (type) {
            case 0:
                buffer = new StringBuffer("0123456789");
                break;
            case 1:
                buffer = new StringBuffer("abcdefghijklmnopqrstuvwxyz");
                break;
            case 2:
                buffer = new StringBuffer("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
                break;
            case 3:
                buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyz");
                break;
            case 4:
                buffer = new StringBuffer("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
                break;
            case 5:
                buffer = new StringBuffer("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
                break;
            case 6:
                buffer = new StringBuffer("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
                sb.append(buffer.charAt(r.nextInt(buffer.length() - 10)));
                passLength -= 1;
                break;
            case 7:
                String s = UUID.randomUUID().toString();
                sb.append(s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24));
        }

        if (type != 7) {
            int range = buffer.length();
            for (int i = 0; i < passLength; ++i) {
                sb.append(buffer.charAt(r.nextInt(range)));
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) throws Exception {
//        System.out.println(getRandomCode(20,6));
//        System.out.println(getName());
//        System.out.println(getTel());
//        System.out.println(getAddress());
//        System.out.println(getChineseName());
//        System.out.println(getEmail(10,20));
//        read("d:/猎聘网.xml");
        System.out.println(randomBirth(10, 20));
        System.out.println(getSala());
    }

    /**
     * 自我评价
     *
     * @return
     */
    public static String getGRPJ() {
        String[] s = {"空山不见人，但闻人语响,返景入深林，复照青苔上。", "唐宋八大家，又称唐宋古文八大家，是中国唐代韩愈，柳宗元和宋代苏轼、苏洵、苏辙、王安石、曾巩、欧阳修八位散文家的合称。其中韩愈、柳宗元是唐代古文运动的领袖，欧阳修、三苏等四人是宋代古文运动的核心人物，王安石、曾巩是临川文学的代表人物。韩愈是“古文运动”的倡导者，他们先后掀起的古文革新浪潮，使诗文发展的陈旧面貌焕然一新。",
                "本人性格开朗、稳重、有活力，待人热情、真诚；工作认真负责，积极主动，能吃苦耐劳，用于承受压力，勇于创新；有很强的组织能力和团队协作精神，具有较强的适应能力；纪律性强，工作积极配合；意志坚强，具有较强的无私奉献精神。",
                "对待工作认真负责，善于沟通、协调有较强的组织能力与团队精神；活泼开朗、乐观上进、有爱心并善于施教并行；上进心强、勤于学习能不断提高自身的能力与综合素质。在未来的工作中，我将以充沛的精力，刻苦钻研的精神来努力工作，稳定地提高自己的工作能力，与企业同步发展。",
                "本人是设计专业毕业生，熟练手绘，熟练cad,3dmax,vray,photoshop等设计软件，熟练办公软件。懂画施工图，懂一定的对装饰材料和施工方法。本人乐观开朗,积极好学,健谈,有自信,具有设计的创新思想;对待工作认真负责,细心,能够吃苦耐劳,敢于挑战,并且能够很快融于集体。我虽刚刚毕业，但我年轻，有朝气，有能力完成任何工作。尽管我还缺乏一定的经验，但我会用时间和汗水去弥补。请领导放心，我一定会保质保量完成各项工作任务。 思想上积极要求上进，团结同学，尊敬师长，乐于助人，能吃苦耐劳，为人诚恳老实，性格开朗善于与人交际，工作上有较强的组织管理和动手能力，集体观念强，具有团队协作精神，创新意识。",
                "通过两年的社会生活，我成长了很多。，我对自己这两年来的收获和感受作一个小结，并以此为我今后行动的指南。思想方面，我追求上进，思想觉悟有了很大的提高。在我从事办公室文员工作过程中，感觉到了办公室文员这一职位在企业运转过程起着衔接与协调重要作用。作为一名办公室文员，要热爱本职工作，兢兢业业，要有不怕苦不怕累的精神，也要有甘当无名英雄的气概。办事要公道，忠于职守并在工作中努力掌握各项技能。",
                "三年的军校生活，使我懂得了纪律得重要性，锻炼了我的意志；乐观向上，大方开朗，热情务实；善与人交流，人际关系良好，待人诚恳；工作认真负责，具有吃苦耐劳、艰苦奋斗的精神；遇事沉着冷静，理智稳重，适应能力强，具备良好的组织协调能力，注重团队精神、爱好阅读,上网,打羽毛球,旅游。",
                "本人热爱生活，性格开朗活泼，乐观向上，乐于助人，乐于进取，积极勤奋，有团队精神，拥有充实的专业知识，也有独立的思维能力，工作态度认真，乐于与人交往，对艺术有着浓厚的兴趣，从小热爱绘画，热爱设计，在校期间曾参加过班级和校园的绘画展览，手绘能力强，熟练cad, 3d max, photoshop,coreldraw等设计软件.有一定的相关工作经验，绘画过许多大型室内空间效果图，本人的博客里有自己设计的3d效果图.希望能成为各大企业一份子,今后务必尽自己能力为贵企业出一份力。",
                "作认真负责，不推卸责任；能承受工作中的压力；工作上可以独当一面；具有团队精神，能与同事，其它部门积极配合，公司利益至上；服从性好，能与上司保持良好的沟通，尊重上司的安排；为人诚实，正直；且好学上进，不断提高工作能力；相信您的选择会让您我更加成功；",
                "本人热爱学习，工作态度严谨认真，责任心强，有很好的团队合作能力。有良好的分析、解决问题的思维。以创新、解决客户需求、维护公司利益为宗旨。来接受挑战和更大的发展平台。",
                "诚实、稳重、勤奋、积极上进，拥有丰富的大中型企业管理经验，有较强的团队管理能力，良好的沟通协调组织能力，敏锐的洞察力，自信是我的魅力。我有着良好的形象和气质，健康的体魄和乐观的精神使我能全身心地投入到工作当中。"};

        return s[r.nextInt(s.length)];

    }


    /**
     * 随机城市
     */
    public static String getCity() {
        String citys[] = {"北京", "广东", "山东", "江苏", "河南", "上海", "河北", "浙江", "香港", "山西", "陕西", "湖南", "重庆", "福建", "天津", "云南", "四川", "广西", "安徽", "海南", "江西", "湖北", "山西", "辽宁", "内蒙古"};
        return citys[r.nextInt(citys.length)];
    }

    public static String getSex() {
        return r.nextInt(3) % 2 == 0 ? "男" : "女";
    }

    /**
     * 随机最高学历
     */
    public static String getStudey_maxL() {
        String s[] = {"博士研究生",
                "硕士研究生",
                "大学本科",
                "大专",
                "高中"
        };
        return s[r.nextInt(s.length)];
    }

    /**
     * 随机最高学位
     */
    public static String getStudey_max() {
        String s[] = {"博士学位", "硕士学位", "学士学位"};
        return s[r.nextInt(s.length)];
    }

    /**
     * 随机招聘种类
     */
    public static String getZPZL() {
        String s[] = {"校园招聘", "社会招聘", "实习生招聘"};
        return s[r.nextInt(s.length)];
    }

    /**
     * 政治面貌
     */

    public static String getZZMM() {
        String s[] = {"中国共产党党员",
                "中国共产党预备党员",
                "中国共产主义青年团团员",
                "中国国民党革命委员会会员",
                "中国民主同盟盟员",
                "中国民主建国会会员",
                "中国民主促进会会员",
                "中国农工民主党党员",
                "中国致公党党员",
                "九三学社社员",
                "台湾民主自治同盟盟员",
                "无党派民主人士",
                "群众",
                "港澳同胞"};
        return s[r.nextInt(s.length)];
    }


    /**
     * 随机是否
     *
     * @return
     */
    public static String getYesNO() {
        return r.nextInt(3) % 2 == 0 ? "是" : "否";
    }


    /**
     * 特长
     *
     * @return
     */
    public static String getTC() {
        String s[] = {"电脑应用熟练,熟练操作Word,Excel,,Outlook, ERP等各种办公软件;",
                "勤奋上进好学,工作细心,条理清晰,踏实肯干,有很强的责任心;",
                "性格开朗乐观,善于人际交流和沟通,具备团队合作精神;",
                "喜好书法并有一定的书写能力。",
                "阅读,游泳,篮球,演讲,辩论性格开朗、待人热情、工作细心、办事高效、能吃苦耐劳、有较强的组织能力办事认真、责任心强、勤奋好学、善于总结、虚心好学待人热情、办事稳重认真、有事业心。能够熟练操作常用办公软件word、excel、ppt等,对网络管理有一定了解。",
                "有较好的文字写作功底,口头表达能力较强。开朗、谦虚、自律、自信、勤奋好学、脚踏实地、认真负责、坚毅不拔、适应力强、吃苦耐劳、思维敏捷、勇于创新,敢于迎接新挑战。",
                "思想方面:在思想方面,我始终要求自己与时俱进,积极了解国内外时事政治,认真学习先进理论知识。在课余,我会广泛阅读各类杂志文章,并逐渐学会了从事物的表面信息去分析其内在缘由。同时,我经常与朋友和长辈交流一些对时政的看法,集思广益,从中吸取教训和经验,不断提高自身思想素质。预备期间,我思想上最大的转变和收获就是学会了不再纠结于“想要做”,而直接去思考“该怎么做”,不把时间浪费在犹豫和徘徊中",
                "学习方面:在学习方面,我平时认真听讲,及时复习,课后也会与同学讨论有关问题,但学习成绩依然是我的弱项。可是我想学习却不应该一直是我的弱项,可能是我还没有找到最适合我的学习方法,无法更好得将所学的知识化为成果。我的成绩较平稳,总是在七、八十徘徊,上学期绩点2.68,比大二有些许下降。但我会一直努力,不断的增强自身的综合竞争能力。",
                "工作方面:在工作方面,我曾担任班级宣传委员、邓读小组长兼副班长,也是自己寝室的寝室长。在各个岗位上,我都能踏实工作,切实履行自身义务与责任,同时积极与其他班委交流,共同建设好我们的班集体。在这方面我最大的收获是,无论对正在进行的活动有什么意见或不满,都要配合集体搞完活动再说,一切以集体利益为先。",
                "生活方面:在生活方面,我自强自立,关心同学,帮助同学,和同学们相处融洽,也能帮助解决同学们间的矛盾。经常和寝室同学一起活动。"};
        return s[r.nextInt(s.length)];
    }


    /**
     * 随机工作单位
     */
 /*   public static String getCompanyName(){

        List<String> CompanyNames=new ArrayList<>();
        for (JobInfo jobInfo : jobInfos) {
            String companyName = jobInfo.getCompanyName();
            CompanyNames.add(companyName);
        }
        return CompanyNames.get(r.nextInt(CompanyNames.size()));
    }*/


    /**
     * 随机岗位名称
     *
     *
     */
  /*  public static String getJobName(){

        List<String> jobNameS=new ArrayList<>();
        for (JobInfo jobInfo : jobInfos) {
            String jobName = jobInfo.getJobName();
            jobNameS.add(jobName);
        }
        return jobNameS.get(r.nextInt(jobNameS.size()));
    }*/


    /**
     * 工作内容
     */
   /* public static String getJobInfo(){
        List<String> jobNameS=new ArrayList<>();
        for (JobInfo jobInfo : jobInfos) {
            String jobName = jobInfo.getJobInfo();
            jobNameS.add(jobName);
        }
        return jobNameS.get(r.nextInt(jobNameS.size()));
    }*/


    /**
     * 随机专业
     *
     * @return
     */
    public static String getA0432() {
        String s[] = {"挖掘机", "计算机与技术", "京生管理", "毛概", "音乐", "英语", "法语", "法律", "材料技术", "大数据", "物联网", "火电厂集控", "电子科技", "自动化"};
        return s[r.nextInt(s.length)];
    }


    /**
     * 毕业院校
     *
     * @return
     */
    public static String getA0431() {

        String s[] = {"中国人民大学",
                "清华大学",
                "北京交通大学",
                "北京工业大学",
                "北京航空航天大学",
                "北京理工大学",
                "北京科技大学",
                "北方工业大学",
                "北京化工大学",
                "北京工商大学",
                "北京服装学院",
                "北京邮电大学",
                "北京印刷学院",
                "北京建筑大学",
                "北京石油化工学院",
                "北京电子科技学院",
                "中国农业大学",
                "北京农学院",
                "北京林业大学",
                "北京协和医学院",
                "首都医科大学",
                "北京中医药大学",
                "北京师范大学",
                "首都师范大学",
                "首都体育学院",
                "北京外国语大学",
                "北京第二外国语学院",
                "北京语言大学",
                "中国传媒大学",
                "中央财经大学",
                "对外经济贸易大学",
                "北京物资学院",
                "首都经济贸易大学",
                "外交学院",
                "中国人民公安大学",
                "国际关系学院",
                "北京体育大学",
                "中央音乐学院",
                "中国音乐学院",
                "中央美术学院",
                "中央戏剧学院",
                "中国戏曲学院",
                "北京电影学院",
                "北京舞蹈学院",
                "中央民族大学",
                "中国政法大学",
                "华北电力大学",
                "中华女子学院",
                "北京信息科技大学",
                "中国矿业大学（北京）",
                "中国石油大学（北京）",
                "中国地质大学（北京）",
                "北京联合大学",
                "北京城市学院",
                "中国青年政治学院",
                "首钢工学院",
                "中国劳动关系学院",
                "北京吉利学院",
                "首都师范大学科德学院",
                "北京工商大学嘉华学院",
                "北京邮电大学世纪学院",
                "北京工业大学耿丹学院",
                "北京警察学院",
                "北京第二外国语学院中瑞酒店管理学院",
                "中国科学院大学",
                "中国社会科学院大学",
                "北京工业职业技术学院",
                "北京信息职业技术学院",
                "北京电子科技职业学院",
                "北京京北职业技术学院",
                "北京交通职业技术学院",
                "北京青年政治学院",
                "北京农业职业学院",
                "北京政法职业学院",
                "北京财贸职业学院",
                "北京北大方正软件职业技术",
                "101",
                "北京经贸职业学院",
                "北京经济技术职业学院",
                "北京戏曲艺术职业学院",
                "北京汇佳职业学院",
                "北京科技经营管理学院",
                "北京科技职业学院",
                "北京培黎职业学院",
                "北京经济管理职业学院",
                "北京劳动保障职业学院",
                "北京社会管理职业学院",
                "北京艺术传媒职业学院",
                "北京体育职业学院",
                "北京交通运输职业学院",
                "北京卫生职业学院",
                "北京网络职业学院",
                "北京市海淀区职工大学",
                "北京市东城区职工业余大学",
                "北京市崇文区职工大学",
                "北京宣武红旗业余大学",
                "北京市石景山区业余大学",
                "北京市朝阳区职工大学",
                "北京医药集团职工大学",
                "北京市总工会职工大学",
                "中央党校继续教育学院",
                "中国记协职工新闻学院",
                "中南海业余大学",
                "北京教育学院",
                "北京开放大学",
                "国家开放大学（中央广播电视大学）",
                "民航管理干部学院",
                "北京市丰台区职工大学",
                "北京市西城经济科学大学",
                "北京市农工商联合总公司职工大学",
                "公安部管理干部学院",
                "国家法官学院",
                "华北电业联合职工大学",
                "首都联合职工大学",
                "国家检察官学院",
                "天津市",
                "南开大学",
                "天津大学",
                "天津科技大学",
                "天津工业大学",};
        return s[r.nextInt(s.length)];
    }

    /**
     * 外语种类
     */
    public static String getWYZL() {
        String s[] = {"英语",
                "德语",
                "CET-6",
                "专业四级",
                "专业八级",
                "雅思",
                "托福",
                "其他",
                "法语",
                "公共四级",
                "专业四级",
                "专业八级"};
        return s[r.nextInt(s.length)];
    }


    /**
     * 获取薪水
     *
     * @return
     */
    public static String getSala() {
//        List<String> jobNameS=new ArrayList<>();
//        for (JobInfo jobInfo : jobInfos) {
//            String jobName = String.valueOf(jobInfo.getSalaryMax());
//            jobNameS.add(jobName);
//        }
//        String s = jobNameS.get(r.nextInt(jobNameS.size()));
//        ${qz_list.MQNX?number *12/1000}万(${qz_list.MQNX}元/月*12个月)
        String s = "";
        int num = getNum(5000, 50000);

        int i = num * 12 / 10000;
        s = i + "万(" + num + "元/月*12个月)";

        return s;
    }

    public static String getSala2() {
//        List<String> jobNameS=new ArrayList<>();
//        for (JobInfo jobInfo : jobInfos) {
//            String jobName = String.valueOf(jobInfo.getSalaryMax());
//            jobNameS.add(jobName);
//        }
//        String s = jobNameS.get(r.nextInt(jobNameS.size()));
//        ${qz_list.MQNX?number *12/1000}万(${qz_list.MQNX}元/月*12个月)
        String s = "";
        int num = getNum(5000, 50000);

        int i = num * 12 / 10000;
        s = i + "万元";

        return s;
    }

    public static String getSala3() {
//        List<String> jobNameS=new ArrayList<>();
//        for (JobInfo jobInfo : jobInfos) {
//            String jobName = String.valueOf(jobInfo.getSalaryMax());
//            jobNameS.add(jobName);
//        }
//        String s = jobNameS.get(r.nextInt(jobNameS.size()));
//        ${qz_list.MQNX?number *12/1000}万(${qz_list.MQNX}元/月*12个月)
        String s = "";
        int num = getNum(5000, 50000);

        int i = num * 12 / 10000;

        s = i + "-" + (i + 10) + "万 元/年";

        return s;
    }

    /**
     * 转换为中国人民币大写字符串,精确到分
     *
     * @param money 传入小写数字字符串
     * @return
     * @throws Exception
     */
    public static String toChinaUpper(String money)  {
        boolean lessZero = false;
        if (money.startsWith("-")) {
            money = money.substring(1);
            lessZero = true;
        }

        if (!money.matches("^[0-9]*$|^0+\\.[0-9]+$|^[1-9]+[0-9]*$|^[1-9]+[0-9]*.[0-9]+$")) {
            System.out.println("钱数格式错误！");
        }
        String[] part = money.split("\\.");
        String integerData = part[0];
        String decimalData = part.length > 1 ? part[1] : "";
        //替换前置0
        if (integerData.matches("^0+$")) {
            integerData = "0";
        } else if (integerData.matches("^0+(\\d+)$")) {
            integerData = integerData.replaceAll("^0+(\\d+)$", "$1");
        }

        StringBuffer integer = new StringBuffer();
        for (int i = 0; i < integerData.length(); i++) {
            char perchar = integerData.charAt(i);
            integer.append(upperNumber(perchar));
            integer.append(upperNumber(integerData.length() - i - 1));
        }
        StringBuffer decimal = new StringBuffer();
        if (part.length > 1 && !"00".equals(decimalData)) {
            int length = decimalData.length() >= 2 ? 2 : decimalData.length();
            for (int i = 0; i < length; i++) {
                char perchar = decimalData.charAt(i);
                decimal.append(upperNumber(perchar));
                if (i == 0) {
                    decimal.append('角');
                }
                if (i == 1)
                    decimal.append('分');
            }
        }
        String result = integer.toString() + decimal.toString();
        result = dispose(result);
        if (lessZero && !"零圆整".equals(result)) {
            result = "负" + result;
        }
        return result;
    }

    private static char upperNumber(char number) {
        switch (number) {
            case '0':
                return '零';
            case '1':
                return '壹';
            case '2':
                return '贰';
            case '3':
                return '叁';
            case '4':
                return '肆';
            case '5':
                return '伍';
            case '6':
                return '陆';
            case '7':
                return '柒';
            case '8':
                return '捌';
            case '9':
                return '玖';
        }
        return '0';
    }

    private static char upperNumber(int index) {
        int realIndex = index % 9;
        if (index > 8) {//亿过后进入回归,之后是拾,佰...
            realIndex = (index - 9) % 8;
            realIndex = realIndex + 1;
        }
        switch (realIndex) {
            case 0:
                return '圆';
            case 1:
                return '拾';
            case 2:
                return '佰';
            case 3:
                return '仟';
            case 4:
                return '万';
            case 5:
                return '拾';
            case 6:
                return '佰';
            case 7:
                return '仟';
            case 8:
                return '亿';
        }
        return '0';
    }

    private static String dispose(String result) {
        result = result.replaceAll("0", "");//处理
        result = result.replaceAll("零仟零佰零拾|零仟零佰|零佰零拾|零仟|零佰|零拾", "零");
        result = result.replaceAll("零+", "零").replace("零亿", "亿");
        result = result.matches("^.*亿零万[^零]仟.*$") ? result.replace("零万", "零") : result.replace("零万", "万");
        result = result.replace("亿万", "亿");
        //处理小数
        result = result.replace("零角", "零").replace("零分", "");
        result = result.replaceAll("(^[零圆]*)(.+$)", "$2");
        result = result.replaceAll("(^.*)([零]+圆)(.+$)", "$1圆零$3");

        //处理整数单位
        result = result.replaceAll("圆零角零分|圆零角$|圆$|^零$|圆零$|零圆$", "圆整");
        result = result.replaceAll("^圆整$", "零圆整");


        return result;
    }


}