package com.soychristian.stalker;

import com.soychristian.stalker.commands.CommandManager;
import com.soychristian.stalker.exceptions.BadFormatConfigurationFileException;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Stalker extends JavaPlugin {

    private static final String PLUGIN_NAME = "Stalker";
    private static final String PLUGIN_NAME_COLOR = ChatColor.translateAlternateColorCodes('&', "&6&l[&f&l" + PLUGIN_NAME + "&6&l] &f&l> &r");
    /*
    1. Buscar forma de agregar colores a terminal e incluir prefijo para el nombre del plugin.
        - getLogger().info() VS getServer().getConsoleSender().sendMessage()
        - &6&l[&f&lStalker&6&l] &f&l> &r
    2. Cambiar el lugar de compilación del plugin.
    3. Socializar el script para reiniciar el servidor.
        - .sh: para Linux
        - .bat: para Windows
    4. Como utilizar Git para lo más básico.
        - git add . (Agregar los archivos a los que realizar seguimiento)
        - git commit -m "Mensaje" (Crear copia de los archivos)
        - git push (Agregar a repositorio remoto)
        - git reset --hard (Regresar a último commit)
    ---

    (Recordar como terminar los procesos infinitos)
    5. Subida a repositorio remoto (GitHub).
        5.1. Preparación .README y .gitignore
    6. Tratamiento de errores personalizado.

    ---
    6. Gestor de comandos

    ---
    7. Gestor de eventos.

    * REQUERIMIENTOS:
    * - Se debe interactuar con el 100% del plugin DENTRO del juego. (interfaces)
    * - Tod0 evento/acción dentro del servidor debe ser "stalkeable".
    *    - El usuario puede activar y desactivar los que desee.
    * - Se deben poder realizar QUERYS (tipo base de datos) para la visualización de los datos.
    * - (Por el momento) toda la información se almacenará de forma local.
    *   - Cuando el plugin este finalizado, se realizará integración con base de datos relacional y no relacional.
    * - Soporte a multiples idiomas con una plantilla YAML pre-definida.
    * */

    @Override
    public void onEnable() {
        setupConfiguration();
        ConsoleCommandSender commandSender = this.getServer().getConsoleSender();

        FileConfiguration config = this.getConfig();
        Object valueEnableObject = config.get("enable-plugin");
        boolean valueEnable = false;

        try {
            if (valueEnableObject instanceof Boolean){
                valueEnable = (Boolean) valueEnableObject;
            } else {
                throw new BadFormatConfigurationFileException("config.yml", "enable-plugin");
            }
        } catch (BadFormatConfigurationFileException e){
            commandSender.sendMessage(String.format("%sEl valor de la propiedad %s no es el indicado en el archivo de configuración %s. Se espera un booleano.", PLUGIN_NAME_COLOR, e.getConfigurationPath(), e.getConfigurationFilename()));
        }

        if (!valueEnable){
            commandSender.sendMessage(PLUGIN_NAME_COLOR + "El plugin ha sido desactivado debido a la opción enable-plugin de config.yml");
            getServer().getPluginManager().disablePlugin(this);
        }
        this.getCommand(PLUGIN_NAME.toLowerCase()).setExecutor(new CommandManager(this));
    }

    public void setupConfiguration(){
        this.saveDefaultConfig();
        FileConfiguration config = this.getConfig();
        if (!config.contains("enable-plugin")){
            config.set("enable-plugin", true);
        }
        this.saveConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static String getPluginName(){
        return PLUGIN_NAME;
    }
    public static String getPluginNameColor(){
        return PLUGIN_NAME_COLOR;
    }
}
