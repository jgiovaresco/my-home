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
    // Don't use Storybook's default SVG Configuration
    config.module.rules = config.module.rules.map((rule) => {
      if (rule.test.toString().includes('svg')) {
        const test = rule.test
          .toString()
          .replace('svg|', '')
          .replace(/\//g, '');
        return { ...rule, test: new RegExp(test) };
      } else {
        return rule;
      }
    });

    // Assign aliases from snowpack.config.js
    config.resolve.alias = {
      ...config.resolve.alias,
      ...snowpackConfig.alias,
    };
    // Add rules for supporting import.meta
    config.module.rules.push(
      {
        test: /\.tsx?$/,
        use: [
          require.resolve('@open-wc/webpack-import-meta-loader'),
          require.resolve(
            '@snowpack/plugin-webpack/plugins/proxy-import-resolve',
          ),
        ],
      },
      // Use SVG Configuration for SVGR yourself
      {
        test: /\.svg$/,
        use: ['@svgr/webpack'],
      },
    );
    // Add __SNOWPACK_ENV__ global
    config.plugins.push(
      new webpack.DefinePlugin({
        __SNOWPACK_ENV__: JSON.stringify(process.env),
      }),
    );
    return config;
  },
};
