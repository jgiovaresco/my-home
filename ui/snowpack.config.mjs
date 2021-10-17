import proxy from 'http2-proxy';

/** @type {import("snowpack").SnowpackUserConfig } */
export default {
  mount: {
    public: { url: '/', static: true },
    src: { url: '/dist' },
  },
  plugins: [
    '@snowpack/plugin-sass',
    '@snowpack/plugin-react-refresh',
    '@snowpack/plugin-dotenv',
    '@snowpack/plugin-typescript',
    'snowpack-plugin-svgr',
    // ['snowpack-plugin-svgr', { svgrOptions: { icon: true } }],
  ],
  routes: [
    /* Enable an SPA Fallback in development: */
    // {"match": "routes", "src": ".*", "dest": "/index.html"},
    {
      src: '/api/.*',
      dest: (req, res) => {
        return proxy.web(req, res, {
          hostname: 'localhost',
          port: 8080,
        });
      },
    },
  ],
  optimize: {
    /* Example: Bundle your final build: */
    // "bundle": true,
  },
  packageOptions: {
    /* ... */
  },
  devOptions: {
    port: 3000,
  },
  buildOptions: {
    /* ... */
  },
};
