import React from 'react';
import { Navigation, NavigationProps } from '../navigation';

import './header.scss';

export interface HeaderProps extends NavigationProps {}

export const Header: React.FC<HeaderProps> = ({
  navItems = [],
  isAuthenticated,
  onLogout,
  onLogin,
}) => (
  <header>
    <h1 className="title">My Library</h1>

    <Navigation
      navItems={navItems}
      isAuthenticated={isAuthenticated}
      onLogin={onLogin}
      onLogout={onLogout}
    />
  </header>
);
