package it.unibo.biscia.view.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader.FreeTypeFontLoaderParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ObjectMap;

/**
 * A decorator for the asset manager.
 *
 */
public final class AssetManagerDecorator extends AssetManager {

    public AssetManagerDecorator() {
        super();

        // set loaders for .ttf files (True Type fonts)
        FileHandleResolver resolver = new InternalFileHandleResolver();
        this.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        this.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
    }

    /**
     * Builds font's parameters and loads it.
     * 
     * @param fontAsset The asset of type FontManager.Font
     */
    public void loadFont(final Asset<FontManager.Font> fontAsset) {
        // TODO: maybe i can improve this code generating the font my self
        // generates font's parameters
        FreeTypeFontLoaderParameter params = new FreeTypeFontLoaderParameter();
        params.fontFileName = fontAsset.getPath();
        params.fontParameters.size = fontAsset.getInfo().getSize();
        // generating the BitmapFont from the TrueTypeFont provided and loading it in
        // the manager
        this.load(fontAsset.getName(), BitmapFont.class, params);
    }

    /**
     * Build's a skin with its resources and loads it.
     * 
     * @param skinAsset The asset of type SkinManager.Skin
     */
    public void loadSkin(final Asset<SkinManager.Skin> skinAsset) {
        // creating the resources map to load with the skin .json file
        // it maps resource name with its istance
        final ObjectMap<String, Object> resources = new ObjectMap<String, Object>();
        // for every resource's name in the skin's info, get its instance inside the
        // manager (via this.get()) and put it inside the ObjectMap
        skinAsset.getInfo().getResources().stream().map(a -> a.getName()).forEach(n -> resources.put(n, this.get(n)));
        // loads the skin with its resources
        this.load(skinAsset.getPath(), Skin.class, new SkinLoader.SkinParameter(resources));
    }

    @Override
    public synchronized void dispose() {
        // must add this line for a known bug of libGDX. See:
        // https://stackoverflow.com/questions/39408448/assetmanager-and-skin-dispose-of-font-skin
        this.get(SkinManager.MAIN.getPath(), Skin.class).remove(FontManager.ARCADE.getName(), BitmapFont.class);
        super.dispose();
    }

}
