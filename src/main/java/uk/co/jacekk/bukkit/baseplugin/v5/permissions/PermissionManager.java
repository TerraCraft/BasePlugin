package uk.co.jacekk.bukkit.baseplugin.v5.permissions;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.bukkit.permissions.Permission;

import uk.co.jacekk.bukkit.baseplugin.v5.BaseObject;
import uk.co.jacekk.bukkit.baseplugin.v5.BasePlugin;

public class PermissionManager extends BaseObject<BasePlugin> {
	
	public PermissionManager(BasePlugin plugin){
		super(plugin);
	}
	
	/**
	 * Registers the default permissions for a plugin
	 * 
	 * @param permissions	An array of {@link PluginPermission}s to register.
	 */
	public void registerPermissions(PluginPermission[] permissions){
		for (PluginPermission permission : permissions){
			plugin.pluginManager.addPermission(new Permission(permission.getNode(), permission.getDescription(), permission.getDefault()));
		}
	}
	
	/**
	 * Registers the default permissions for a plugin
	 * 
	 * @param permissionHolder	The class holding all of the {@link PluginPermission} objects.
	 */
	public void registerPermissions(Class<?> permissionHolder){
		List<PluginPermission> perms = Arrays.asList();
		
		for (Field field : permissionHolder.getDeclaredFields()){
			if (field.getType().equals(PluginPermission.class)){
				try{
					perms.add((PluginPermission) field.get(null));
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}
		
		this.registerPermissions(perms.toArray(new PluginPermission[0]));
	}
	
}
