import 'tslib';
import React from 'react';
import ReactDOM from 'react-dom';
import {
  Auth0ProviderOptions,
  AppState,
  Auth0Context,
} from '@auth0/auth0-react';
import { Auth0Provider } from '@auth0/auth0-react';
import { App } from './App';
import { getAuth0Config, setTokenAccessor } from './auth';
import './index.scss';

const onRedirectCallback = (appState: AppState) => {};
const config = getAuth0Config();

const providerConfig: Auth0ProviderOptions = {
  domain: config.domain,
  clientId: config.clientId,
  ...(config.audience ? { audience: config.audience } : null),
  redirectUri: window.location.origin,
  onRedirectCallback,
};

ReactDOM.render(
  <React.StrictMode>
    <Auth0Provider {...providerConfig}>
      <Auth0Context.Consumer>
        {({ getIdTokenClaims }) => {
          setTokenAccessor(getIdTokenClaims);
          return <App />;
        }}
      </Auth0Context.Consumer>
    </Auth0Provider>
  </React.StrictMode>,
  document.getElementById('root'),
);

// Hot Module Replacement (HMR) - Remove this snippet to remove HMR.
// Learn more: https://snowpack.dev/concepts/hot-module-replacement
if (import.meta.hot) {
  import.meta.hot.accept();
}
