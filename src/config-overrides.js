const { useBabelRc, override } = require('customize-cra');
const path = require("path");

const alterConfig = () => (config, env) => {
  const oneOfLoc = config.module.rules.findIndex(n => n.oneOf);
  console.log(oneOfLoc);

  config.module.rules[oneOfLoc].oneOf = [
    //例如要增加处理less的配置
    {
      test: /\.less$/,
      use: [
        require.resolve('style-loader'),
        {
          loader: require.resolve('css-loader'),
          options: {
            importLoaders: 1,
          },
        },
        {
          loader: 'less-loader'
        }
      ],
    },
    ...config.module.rules[oneOf_loc].oneOf
  ]
  return config;
}

const configs = override(
  alterConfig(),//将自定义配置组合进来
  useBabelRc()//启用babelrc配置文件
);

module.exports = configs;
