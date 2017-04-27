**使用流程说明：**

  AndroidPermissions依赖库,需要在app module下的build.grale中配置如下：

  compile 'com.github.hongyangJia:AndroidPermissions:1.0.2'

  在项目下的build.gradle中需要增加maven地址:

    allprojects {
        repositories {
            jcenter()
            maven {
               maven { url 'https://jitpack.io' }
            }
        }
    }

**一.简易获取权限:**

    1.简易请求权限
      1.1 请求单个权限
     Grace.requestCamera(this);
      1.2 请求多个权限
     Grace.requestMicrophoneCameraStorage(this);

    2.系统权限回调
      @Override
         public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
             Grace.onRequestPermissionsResult(this,requestCode, permissions, grantResults);
         }
  
    3.权限申请成功
       public void onPermission(int requestCode) {
            switch (requestCode) {
                case  Grace.State.CAMERA:
                    break;
                case  Grace.State.MICROPHONE_CAMERA_STORAGE:
                    break;
            }
        }
        
    说明：简易求权限只有一个成功回调接口，如果请求权限被禁止/不再提醒/拒绝..本程序内部已经处理    

**二.自定义请求:**

      1.自定义请求权限
        GracePermission.with(this).permissions(Manifest.permission.CAMERA).requestCode(200).request();
      
      2.系统权限回调
       Grace.onRequestPermissionsResult(this,requestCode, permissions, grantResults);
       或
       GracePermission.onRequestPermissionsResult(this,requestCode, permissions, grantResults);
       
      3.权限申请成功
       public void onPermission(int requestCode) {
                  switch (requestCode) {
                      case 200:
                          break;
                  }
              }
      说明：自定义请求权限只有一个成功回调接口，如果请求权限被禁止/不再提醒/拒绝..本程序内部已经处理   
