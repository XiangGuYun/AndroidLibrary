package yxd.design_mode.creation.abstract_factory.ab_factory;

import yxd.design_mode.creation.abstract_factory.ab_product.DesktopComputer;
import yxd.design_mode.creation.abstract_factory.ab_product.NotebookComputer;

/**
 * Created by asus on 2017/12/19.
 */

public abstract class ComputerFactory {
    public abstract DesktopComputer createDesktopComputer();
    public abstract NotebookComputer createNotebookComputer();
}
