# WebView的一些使用方法
在项目中使用WebVeiw，这里记录一下使用方法

## WebView的基础用法
1.创建WebView，这里是动态创建
```Java
    webView = new WebView(getApplicationContext());
```
2.设置WebView
```Java
    WebSettings settings = webView.getSettings();
        
    /**
     * 设置WebView属性
     */
    private void setWebViewSettings(WebSettings settings) {
        // 设置缓存策略
        // LOAD_DEFAULT：默认方式，如果存在缓存，并且没有过期，则从缓存获取，否则从网络获取
        // LOAD_CACHE_ELSE_NETWORK: 使用本地缓存，过期了也会使用，如果不存在则从网络加载
        // LOAD_NO_CACHE: 从网络上获取资源，不适用本地缓存
        // LOAD_CACHE_ONLY：只使用本地缓存，不从网络上获取资源
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        // 设置webview加载页面内容编码
        settings.setDefaultTextEncodingName("gbk");
        // 设置webview是否可以访问文件数据
        settings.setAllowFileAccess(true);
        // 设置加载网页时是否加载图片
        settings.setBlockNetworkImage(true);
        // 设置用户触摸是否显示缩放控制图标，隔一段时间会自动消失
        settings.setBuiltInZoomControls(true);
        // 设置默认字体大小，取值1-72，默认值是16
        settings.setDefaultFontSize(16);
        // 设置webview使用的字体，默认是 monospace
        settings.setFixedFontFamily("monospace");
        // 设置页面布局显示式样
        // NARROW_COLUMNS：可能的话使所有列的宽度不超过屏幕宽度
        // NORMAL：正常显示不做任何渲染
        // SINGLE_COLUMN：把所有内容放大webview等宽的一列中
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS); // 设置布局方式
        // 设置用鼠标激活
        settings.setLightTouchEnabled(true);
        // 设置webview是否支持缩放，默认是支持的
        settings.setSupportZoom(true);
        // 设置webview是否支持wiewport
        settings.setUseWideViewPort(true);
        // 设置WebView是否支持多屏窗口
        settings.setSupportMultipleWindows(false);
        // 设置是否开启Application缓存，默认是false
        settings.setAppCacheEnabled(false);
        // 设置是否开启数据库权限，默认是false
        settings.setDatabaseEnabled(false);
        // 是否开启DOM存储权限，默认是false
        settings.setDomStorageEnabled(false);
        // 设置是否支持Javascript交互
        settings.setJavaScriptEnabled(true);
        // 启动地理定位
        settings.setGeolocationEnabled(true);
        // 提供的路径,在H5使用缓存过程中生成的缓存文件
        settings.setAppCachePath(this.getDir("appcache", 0).getPath());
        // 缩放至屏幕大小
        settings.setLoadWithOverviewMode(true);
        // 设置是否显示缩放控件
        settings.setDisplayZoomControls(false);
        // 设置字体大小
        settings.setTextZoom(150);
    }
```
3.加载页面
```Java
    webView.loadUrl(url);
```

## 注意问题
1.如果WebView是动态创建的，需要添加下面代码才会加载页面
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (webView != null)
                    webView.loadUrl(request.getUrl().toString());
                return true;
            }
        }

## license

    Copyright 2019 wjianchen13

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.