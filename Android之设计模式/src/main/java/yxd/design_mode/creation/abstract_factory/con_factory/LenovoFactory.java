package yxd.design_mode.creation.abstract_factory.con_factory;

import yxd.design_mode.creation.abstract_factory.ab_factory.ComputerFactory;
import yxd.design_mode.creation.abstract_factory.ab_product.DesktopComputer;
import yxd.design_mode.creation.abstract_factory.ab_product.NotebookComputer;
import yxd.design_mode.creation.abstract_factory.con_product.LenovoDesktopComputer;
import yxd.design_mode.creation.abstract_factory.con_product.LenovoNotebookComputer;

/**
 * Created by asus on 2017/12/19.
 */

public class LenovoFactory extends ComputerFactory {
    @Override
    public DesktopComputer createDesktopComputer() {
        return new LenovoDesktopComputer();
    }
    @Override
    public NotebookComputer createNotebookComputer() {
        return new LenovoNotebookComputer();
    }
}
