const snowpackConfig = require('../snowpack.config');
const webpack = require('webpack');

module.exports = {
  stories: ['../src/**/*.stories.mdx', '../src/**/*.stories.@(js|jsx|ts|tsx)'],
  addons: [
    '@storybook/addon-links',
    '@storybook/addon-essentials',
    '@storybook/preset-scss',
  ],
  core: {
    builder: 'webpack5',
  },
  webpackFinal: async (config) => {
    // Assign aliases from snowpack.config.js
    config.resolve.alias = {
      ...config.resolve.alias,
      ...snowpackConfig.alias,
    };
    // Add rules for supporting import.meta
    config.module.rules.push({
      test: /\.tsx?$/,
      use: [
        require.resolve('@open-wc/webpack-import-meta-loader'),
        require.resolve(
          '@snowpack/plugin-webpack/plugins/proxy-import-resolve',
        ),
      ],
    });
    // Add __SNOWPACK_ENV__ global
    config.plugins.push(
      new webpack.DefinePlugin({
        __SNOWPACK_ENV__: JSON.stringify(process.env),
      }),
    );
    return config;
  },
};
