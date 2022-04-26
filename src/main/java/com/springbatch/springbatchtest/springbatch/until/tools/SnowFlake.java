package com.springbatch.springbatchtest.springbatch.until.tools;


/**
 * @ClassName SnowFlake.java
 * @PackagePath com.springbatch.springbatchtest.test
 * @Author Dr. zhang
 * @CreateTime 2022/3/17
 * @Description雪花算法,位运算熟练掌握必备
 */
public class SnowFlake {
    public SnowFlake() {

    }

    /**
     * 雪花算法
     *
     * 学习目的：理解基础运算符
     *
     * 64位标识的作用：
     * 1位标识，由于long基本类型在Java中是带符号的，最高位是符号位，正数是0，负数是1，所以id一般是正数，最高位是0
     * 41位时间截(毫秒级)，注意，41位时间截不是存储当前时间的时间截，而是存储时间截的差值（当前时间截 - 开始时间截) 后得到的值，这里的的开始时间截，一般是我们的id生成器开始使用的时间，由我们程序来指定的（如下下面程序IdWorker类的startTime属性）。41位的时间截，可以使用69年，年T = (1L << 41) / (1000L * 60 * 60 * 24 * 365) = 69
     * 10位的数据机器位，可以部署在1024个节点，包括10位workerId
     * 12位序列，毫秒内的计数，12位的计数顺序号支持每个节点每毫秒(同一机器，同一时间截)产生4096个ID序号
     * 加起来刚好64位，为一个Long型
     */

    /**
     * @Description: 雪花算法
     * @Param:
     * @return:
     * @Author: zhangmt
     * @Date: 2022/3/17
     */
    public Long generate() {
        return this.nextId();
    }

    /**
     * 起始的时间戳
     */
    private final static long START_STMP = 1480166465631L;

    /**
     * 每一部分占用的位数
     */
    private final static long SEQUENCE_BIT = 12; //序列号占用的位数
    private final static long MACHINE_BIT = 5;  //机器标识占用的位数
    private final static long DATACENTER_BIT = 5;//数据中心占用的位数

    /**
     * 每一部分的最大值
     */
    private final static long MAX_DATACENTER_NUM = -1L ^ (-1L << DATACENTER_BIT);
    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

    private long datacenterId;  //数据中心
    private long machineId;    //机器标识
    private long sequence = 0L; //序列号
    private long lastStmp = -1L;//上一次时间戳

    public SnowFlake(long datacenterId, long machineId) {
        if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
            throw new IllegalArgumentException("datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.datacenterId = datacenterId;
        this.machineId = machineId;
    }

    /**
     * 产生下一个ID
     *
     * @return
     */
    public synchronized long nextId() {
        long currStmp = getNewstmp();
        if (currStmp < lastStmp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if (currStmp == lastStmp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                currStmp = getNextMill();
            }
        } else {
            //不同毫秒内，序列号置为0
            sequence = 0L;
        }

        lastStmp = currStmp;

        return (currStmp - START_STMP) << TIMESTMP_LEFT //时间戳部分
                | datacenterId << DATACENTER_LEFT      //数据中心部分
                | machineId << MACHINE_LEFT            //机器标识部分
                | sequence;                            //序列号部分
    }

    private long getNextMill() {
        long mill = getNewstmp();
        while (mill <= lastStmp) {
            mill = getNewstmp();
        }
        return mill;
    }

    private long getNewstmp() {
        return System.currentTimeMillis();
    }


    public static void main(String[] args) {
//        SnowFlake snowFlake = new SnowFlake();
//        for (int i = 0; i < (1 << 12); i++) {
//            System.out.println(snowFlake.generate());
//        }
//        System.out.println(Long.toBinaryString(1L));
//        System.out.println(Long.toBinaryString(-1L<<1));
//        System.out.println(Long.toBinaryString(0L));
//        System.out.println(Long.toBinaryString(START_STMP));
//        System.out.println("MAX_DATACENTER_NUM:"+Long.toBinaryString(MAX_DATACENTER_NUM));
//        System.out.println("MAX_MACHINE_NUM:"+Long.toBinaryString(MAX_MACHINE_NUM));
//        System.out.println("MAX_SEQUENCE:"+Long.toBinaryString(MAX_SEQUENCE));

//        System.out.println("-1L,bit:" + Long.toBinaryString(-1L) + "    Long:" + (-1L));
//        System.out.println("-1L << 1,bit:" + Long.toBinaryString(-1L << 1) + "    Long:" + (-1L << 1));
//        System.out.println("-1L >> 2,bit:" + Long.toBinaryString(-1L >> 2) + "    Long:" + (-1L >> 2));
//        System.out.println("-1L >> 20,bit:" + Long.toBinaryString(-1L >> 20) + "    Long:" + (-1L >> 20));
//
//
//        System.out.println("1L << 2,bit:" + Long.toBinaryString(1L << 2) + "    Long:" + (1L << 2));
//        System.out.println("32L:" + Long.toBinaryString(32L) + "   32L >>> 2,bit:" + Long.toBinaryString(32L >>> 2) + "    Long:" + (32L >>> 2));
//        System.out.println("-32L:" + Long.toBinaryString(-32L) + "   -32L >>> 2,bit:" + Long.toBinaryString(-32L >>> 2) + "    Long:" + (-32L >>> 2));
//        System.out.println("-32L:" + Long.toBinaryString(-32L) + "   -32L >> 2,bit:" + Long.toBinaryString(-32L >> 2) + "    Long:" + (-32L >> 2));
//
//        System.out.println();


        //hashmap获得散列表做下标源码测试
        Object key  = "dffsdfsdf";
        int h;  //4个字节 ,32位，扣掉16位，使用高位进行比较
        System.out.println(( (h = key.hashCode()) ^ (h >>> 16)) & (16-1) );


    }
}
