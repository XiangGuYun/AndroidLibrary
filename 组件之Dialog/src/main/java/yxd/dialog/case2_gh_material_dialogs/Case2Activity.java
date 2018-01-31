package yxd.dialog.case2_gh_material_dialogs;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.StackingBehavior;
import com.afollestad.materialdialogs.Theme;

import java.util.ArrayList;
import java.util.List;

import yxd.dialog.R;
/*
https://github.com/afollestad/material-dialogs
 */
public class Case2Activity extends AppCompatActivity {

    public void toast(String text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case2);
    }

    /*
    基本对话框
     */
    public void basic(View view) {
        new MaterialDialog.Builder(this)
                .title(R.string.title)
                .content(R.string.content)
                .positiveText(R.string.agree)
                .negativeText(R.string.disagree)
                .show();
    }

    /*
    可以动态控制对话框的显示和消失
     */
    public void dismissing(View view) {
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title(R.string.title)
                .content(R.string.content)
                .positiveText(R.string.agree)
                .show();
    }

    /*
    显示图标的对话框
    You can limit the maximum size of the icon using the limitIconToDefaultSize(),
    maxIconSize(int size), or maxIconSizeRes(int sizeRes) Builder methods.
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void icon(View view) {
        new MaterialDialog.Builder(this)
                .title(R.string.title)
                .content(R.string.content)
                .positiveText(R.string.agree)
                .icon(getDrawable(R.mipmap.ic_launcher))
                .show();
    }

    /*
    If you have multiple action buttons that together are too wide to fit on one line,
    the dialog will stack the buttons to be vertically oriented.
     */
    public void stacked(View view) {
        new MaterialDialog.Builder(this)
                .title(R.string.title)
                .content(R.string.content)
                .positiveText(R.string.longer_positive)
                .negativeText(R.string.negative)
                //.stackingBehavior(StackingBehavior.ADAPTIVE)//按钮自适应
                //.stackingBehavior(StackingBehavior.ALWAYS)//按钮总是纵向堆叠
                .stackingBehavior(StackingBehavior.NEVER)//不使用按钮
                .show();
    }

    /*
    添加中性的按钮
     */
    public void neutral(View view) {
        new MaterialDialog.Builder(this)
                .title(R.string.title)
                .content(R.string.content)
                .positiveText(R.string.agree)
                .negativeText(R.string.disagree)
                .neutralText(R.string.more_info)
                .show();
    }

    /*
    按钮回调
     */
    public void callbacks(View view) {
        new MaterialDialog.Builder(this)
                .title(R.string.title)
                .content(R.string.content)
                .positiveText(R.string.agree)
                .negativeText(R.string.disagree)
                .neutralText(R.string.more_info)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        toast("positive");
                    }
                })
                .onNeutral(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        toast("neutral");
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        toast("negative");
                    }
                })
                .onAny(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        toast("any");
                    }
                }).show();
    }

    /*
    确认对话框
    Note: you can use checkbox prompts with list dialogs and input dialogs, too.
     */
    public void checkbox(View view) {
        new MaterialDialog.Builder(this)
                .iconRes(R.mipmap.ic_launcher)
                .limitIconToDefaultSize()
                .title(R.string.example_title)
                .positiveText(R.string.allow)
                .negativeText(R.string.deny)
                .onAny(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        toast("Prompt checked? " + dialog.isPromptCheckBoxChecked());
                    }
                })
                .checkBoxPromptRes(R.string.dont_ask_again, false, null)
                .show();
    }

    /*
    列表对话框
     */
    public void list(View view) {
        new MaterialDialog.Builder(this)
                .title(R.string.title)
                .items(R.array.items)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                    }
                })
                .show();
    }

    /*
    单选对话框
     */
    public void single(View view) {
        new MaterialDialog.Builder(this)
                .title(R.string.title)
                .items(R.array.items)
                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        /**
                         * If you use alwaysCallSingleChoiceCallback(), which is discussed below,
                         * returning false here won't allow the newly selected radio button to actually be selected.
                         **/
                        return true;
                    }
                })
                .positiveText(R.string.choose)
                .show();
    }

    public void colorRadio(View view) {}

    public void multi(View view) {
        new MaterialDialog.Builder(this)
                .title(R.string.title)
                .items(R.array.items)
                .itemsCallbackMultiChoice(null, new MaterialDialog.ListCallbackMultiChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
                        /**
                         * If you use alwaysCallMultiChoiceCallback(), which is discussed below,
                         * returning false here won't allow the newly selected check box to actually be selected
                         * (or the newly unselected check box to be unchecked).
                         * See the limited multi choice dialog example in the sample project for details.
                         **/
                        return true;
                    }
                })
                .positiveText(R.string.choose)
                .show();
    }

    public void colorCheck(View view) {}

    /*
    给列表项分配id
     */
    public void assignIds(View view) {
        new MaterialDialog.Builder(this)
                .title(R.string.socialNetworks)
                .items(R.array.socialNetworks)
                .itemsIds(R.array.itemIds)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        toast(which + ": " + text + ", ID = " + view.getId());
                    }
                })
                .show();
    }

    /*
    自定义列表对话框
    如果想要访问RecyclerView
    RecyclerView list = dialog.getRecyclerView();
     */
    public void customList(View view) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("Item"+(i+1));
        }
        new MaterialDialog.Builder(this)
                .title(R.string.socialNetworks)
                // second parameter is an optional layout manager. Must be a LinearLayoutManager or GridLayoutManager.
                .adapter(new RecyclerAdapter(list), new LinearLayoutManager(this))
                .show();
    }

    /*
    自定义View对话框
    获取自定义View
    View view = dialog.getCustomView();
    获取和设置动作按钮
    View negative = dialog.getActionButton(DialogAction.NEGATIVE);
    View neutral = dialog.getActionButton(DialogAction.NEUTRAL);
    View positive = dialog.getActionButton(DialogAction.POSITIVE);
    dialog.setActionButton(DialogAction.NEGATIVE, "New Title");
     */
    public void customView(View view) {
        boolean wrapInScrollView = false;
        new MaterialDialog.Builder(this)
                .title(R.string.title)
                .customView(R.layout.custom_view, wrapInScrollView)
                .positiveText(R.string.positive)
                .show();
    }

    /*
    设置主题：LIGHT和DARK
     */
    public void theme(View view) {
        new MaterialDialog.Builder(this)
                .content("Hi")
                .theme(Theme.DARK)
                .show();
    }

    /*
    设置颜色
     */
    public void color(View view) {
        new MaterialDialog.Builder(this)
                .title(R.string.title)
                .content(R.string.content)
                .positiveText(R.string.agree)
                .negativeText(R.string.disagree)
                .neutralText(R.string.more_info)
                .titleColorRes(R.color.material_red_500)
                // notice no 'res' postfix for literal color
                .contentColor(Color.WHITE)
                // notice attr is used instead of none or res for attribute resolving
                //.linkColorAttr(R.attr.my_link_color_attr)
                .dividerColorRes(R.color.material_pink_500)
                .backgroundColorRes(R.color.material_blue_grey_800)
                .positiveColorRes(R.color.material_red_500)
                .neutralColorRes(R.color.material_red_500)
                .negativeColorRes(R.color.material_red_500)
                .widgetColorRes(R.color.material_red_500)
                .buttonRippleColorRes(R.color.material_red_500)
                .show();
    }

    public void selector(View view) {
        new MaterialDialog.Builder(this)
                .btnSelector(R.drawable.custom_btn_selector)
                .btnSelector(R.drawable.custom_btn_selector_primary, DialogAction.POSITIVE)
                .btnSelectorStacked(R.drawable.custom_btn_selector_stacked)
                .listSelector(R.drawable.custom_list_and_stackedbtn_selector)
                .show();
    }

    public void gravity(View view) {
        new MaterialDialog.Builder(this)
                .title(R.string.title)
                .content(R.string.content)
                .positiveText(R.string.agree)
                .negativeText(R.string.disagree)
                .neutralText(R.string.more_info)
                .titleGravity(GravityEnum.CENTER)
                .contentGravity(GravityEnum.CENTER)
                .btnStackedGravity(GravityEnum.START)
                .itemsGravity(GravityEnum.END)
                .buttonsGravity(GravityEnum.END)
                .show();
    }
}
