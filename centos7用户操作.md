## 创建用户
```
adduser test1
passwd test1
huang@1234
```
## 授权
sudo命令的授权管理是在sudoers文件里的。可以看看sudoers
```
sudoers
```
bash: sudoers: 未找到命令...
```
whereis sudoers
```
找到这个文件位置之后再查看权限：
```
ls -l /etc/sudoers
```
提示只有只读的权限，如果想要修改的话，需要先添加w权限：
```
chmod -v u+w /etc/sudoers
```
然后就可以添加内容了，在下面的一行下追加新增的用户：
```
vim /etc/sudoers
```
```
root      ALL=(ALL)     ALL  
test1     ALL=(ALL)     ALL  #这个是新增的用户
```
`:wq`保存退出，如果要收回写权限：
```
chmod -v u-w /etc/sudoers
```
## 使用新用户登录，使用sudo：
```
sudo cat /etc/passwd
```
## 删除用户
```
userdel test1
```
