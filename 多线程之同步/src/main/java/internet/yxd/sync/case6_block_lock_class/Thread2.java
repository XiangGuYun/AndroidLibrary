package internet.yxd.sync.case6_block_lock_class;

/**
 * Created by asus on 2017/12/28.
 */

class Thread2 extends Thread
{
    private Example example;

    public Thread2(Example example)
    {
        this.example = example;
    }

    @Override
    public void run()
    {
        example.execute2();
    }

}
