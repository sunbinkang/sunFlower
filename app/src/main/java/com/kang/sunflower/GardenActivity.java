package com.kang.sunflower;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.kang.sunflower.databinding.ActivityGardenBinding;

/**
 * Created by BinKang on 2021/7/12.
 * Des :
 */
public class GardenActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout; // androidx 的 抽屉布局
    private AppBarConfiguration appBarConfiguration; // androidx. Navigation 查询(AppBarConfiguration)：https://zhuanlan.zhihu.com/p/136479775
    private NavController navController; // androidx. Navigation 控制器

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityGardenBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_garden);
        this.drawerLayout = binding.drawerLayout;
        // 拿到布局的garden_nav_fragment(首页布局Fragment画面区域) 赋值 给成员navController
        this.navController = Navigation.findNavController(this, R.id.garden_nav_fragment);

        // NavigationUI使用AppBarConfiguration 对象来管理应用程序显示区域左上角的“导航”按钮的行为
        this.appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()) // 指向给它
                .setDrawerLayout(drawerLayout) // 抽屉布局
                .build();
        // 标题栏 == toolbar
        // Set up ActionBar
        setSupportActionBar(binding.toolbar);
        // 查询 setupActionBarWithNavController：https://zhuanlan.zhihu.com/p/69653419?from_voters_page=true
        // 在标题 左上角菜单 1
        // 通过NavigationUI.setupActionBarWithNavController()方法，将App bar与NavController绑定
        NavigationUI.setupActionBarWithNavController(this, this.navController, this.appBarConfiguration);

        // 当我们点击 左侧栏 [我的花园 按钮] [植物目录 按钮] 就能触发事件 执行...
        // 左侧栏 于此 绑定  （左侧栏--> [我的花园 按钮] [植物目录 按钮]）
        // Set up navigation menu
        NavigationUI.setupWithNavController(binding.navigationView, this.navController);

    }

    // 在标题是 左上角菜单 2
    // 点击 标题上 左上角菜单 触发的返回动作  [点击事件]
    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }
}
