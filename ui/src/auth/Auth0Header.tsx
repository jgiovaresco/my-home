import { useAuth0 } from '@auth0/auth0-react';
import React from 'react';
import { routes } from '../_router';
import { Header } from '../_core/components';

export const Auth0Header = () => {
  const { isAuthenticated, loginWithRedirect, logout } = useAuth0();
  const logoutWithRedirect = () =>
    logout({
      returnTo: window.location.origin,
    });

  const navItems = routes.map(({ path, label, secured }) => ({
    path,
    label,
    secured,
  }));

  return (
    <Header
      navItems={navItems}
      isAuthenticated={isAuthenticated}
      onLogin={loginWithRedirect}
      onLogout={logoutWithRedirect}
    />
  );
};
