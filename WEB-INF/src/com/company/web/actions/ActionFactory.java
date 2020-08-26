package com.company.web.actions;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.company.web.actions.order.AllOrderListAction;
import com.company.web.actions.order.ConfirmOrderAction;
import com.company.web.actions.order.DeleteOrderAction;
import com.company.web.actions.order.MakeOrderAction;
import com.company.web.actions.reserve.ConfirmReserveAction;
import com.company.web.actions.reserve.DeleteReserveAction;
import com.company.web.actions.reserve.ReserveListAction;
import com.company.web.actions.room.ChangeRoomAction;
import com.company.web.actions.room.ChangeRoomFormAction;
import com.company.web.actions.room.CreateRoomAction;
import com.company.web.actions.room.CreateRoomFormAction;
import com.company.web.actions.room.DeleteRoomAction;
import com.company.web.actions.room.FindRoomAction;
import com.company.web.actions.room.RoomsListAction;
import com.company.web.actions.room.SearchRoomAction;
import com.company.web.actions.user.CabinetAction;
import com.company.web.actions.user.LoginAction;
import com.company.web.actions.user.LoginFormAction;
import com.company.web.actions.user.MainAction;
import com.company.web.actions.user.RegisterAction;
import com.company.web.actions.user.RegisterFormAction;


public class ActionFactory {
     private static Map<String,Class<? extends Action>> actions = new HashMap<>();
     static {
         actions.put("/login", LoginAction.class);
         actions.put("/index", MainAction.class);
         actions.put("/", MainAction.class);
         actions.put("/loginForm", LoginFormAction.class);
         actions.put("/registerForm", RegisterFormAction.class);
         actions.put("/register", RegisterAction.class);
    	 actions.put("/cabinet", CabinetAction.class);
         actions.put("/rooms", RoomsListAction.class);
         actions.put("/findRoom", FindRoomAction.class);
         actions.put("/deleteRoom", DeleteRoomAction.class);
         actions.put("/changeRoomForm", ChangeRoomFormAction.class);
         actions.put("/changeRoom", ChangeRoomAction.class);
         actions.put("/orders", AllOrderListAction.class);
         actions.put("/reserves", ReserveListAction.class);
         actions.put("/makeOrder", MakeOrderAction.class);
         actions.put("/confirmOrder", ConfirmOrderAction.class);
         actions.put("/deleteOrder", DeleteOrderAction.class);
         actions.put("/deleteReserve", DeleteReserveAction.class);
         actions.put("/createRoomForm", CreateRoomFormAction.class);
         actions.put("/createRoom", CreateRoomAction.class);
         actions.put("/confirmReserve", ConfirmReserveAction.class);
         actions.put("/search", SearchRoomAction.class);
     }
   
     public static Action getAction(String url) {
        Logger logger = Logger.getLogger(ActionFactory.class);
        Action action1 = null;
        Class<?> action = actions.get(url);
          try {
          action1 = (Action) action.newInstance();
          } catch (InstantiationException | IllegalAccessException  | NullPointerException e) {
          logger.error(e);
         }
         return action1;
       }
}