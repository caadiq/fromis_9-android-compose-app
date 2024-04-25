package com.beemer.unofficial.fromis_9.view.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.beemer.unofficial.fromis_9.R
import com.beemer.unofficial.fromis_9.ui.theme.Gray
import com.beemer.unofficial.fromis_9.ui.theme.NanumSquareRoundBold
import com.beemer.unofficial.fromis_9.ui.theme.Primary
import com.beemer.unofficial.fromis_9.ui.theme.Transparent

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigation(navController = navController)
        }
    ) {
        Box(Modifier.padding(it)) {
            NavigationGraph(navController = navController)
        }
    }
}

@Composable
fun BottomNavigation(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Video,
        BottomNavItem.Schedule,
    )

    NavigationBar(
        modifier = Modifier,
        containerColor = Transparent
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                icon = {
                       Icon(
                           painter = painterResource(id = item.icon),
                           contentDescription = stringResource(id = item.title),
                           modifier = Modifier
                               .width(24.dp)
                               .height(24.dp)
                       )
                },
                label = {
                        Text(
                            text = stringResource(id = item.title),
                            fontSize = 12.sp,
                            fontFamily = NanumSquareRoundBold
                        )
                },
                selected = currentRoute == item.screenRoute,
                onClick = {
                    navController.navigate(item.screenRoute) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Primary,
                    unselectedIconColor = Gray,
                    selectedTextColor = Primary,
                    unselectedTextColor = Gray,
                    indicatorColor = Transparent
                ),
                modifier = Modifier.clickable { }
            )
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
     NavHost(navController = navController, startDestination = BottomNavItem.Home.screenRoute) {
         composable(BottomNavItem.Home.screenRoute) {
             ScreenHome()
         }
         composable(BottomNavItem.Video.screenRoute) {
             VideoScreen()
         }
         composable(BottomNavItem.Schedule.screenRoute) {
             ScheduleScreen()
         }
     }
}

sealed class BottomNavItem(val title: Int, val icon: Int, val screenRoute: String) {
    data object Home : BottomNavItem(title = R.string.str_bottom_nav_home, icon = R.drawable.icon_home, screenRoute = "HOME")
    data object Video : BottomNavItem(title = R.string.str_bottom_nav_video, icon = R.drawable.icon_video, screenRoute = "VIDEO")
    data object Schedule : BottomNavItem(title = R.string.str_bottom_nav_schedule, icon = R.drawable.icon_calendar, screenRoute = "SCHEDULE")
}