package internet.yxd.sync.case2_2sync_method;

/**
 * Created by asus on 2017/12/28.
 */

class Thread1 extends Thread
{
    private Example example;

    public Thread1(Example example)
    {
        this.example = example;
    }

    @Override
    public void run()
    {
        example.execute();
    }

}
