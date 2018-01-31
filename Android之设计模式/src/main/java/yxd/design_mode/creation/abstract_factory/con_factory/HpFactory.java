package yxd.design_mode.creation.abstract_factory.con_factory;

import yxd.design_mode.creation.abstract_factory.ab_factory.ComputerFactory;
import yxd.design_mode.creation.abstract_factory.ab_product.DesktopComputer;
import yxd.design_mode.creation.abstract_factory.ab_product.NotebookComputer;
import yxd.design_mode.creation.abstract_factory.con_product.HpDesktopComputer;
import yxd.design_mode.creation.abstract_factory.con_product.HpNotebookComputer;

/**
 * Created by asus on 2017/12/19.
 */

public class HpFactory extends ComputerFactory {
    @Override
    public DesktopComputer createDesktopComputer() {
        return new HpDesktopComputer();
    }
    @Override
    public NotebookComputer createNotebookComputer() {
        return new HpNotebookComputer();
    }
}
