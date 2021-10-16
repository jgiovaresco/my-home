import React from 'react';
import { NavLink } from 'react-router-dom';
import type { LoginHandler, LogoutHandler } from '../login-btn';

import './navigation.scss';
import { LoginButton } from '../login-btn';

export interface NavItem {
  path: string;
  label: string;
  active?: boolean;
  secured?: boolean;
}

export interface NavigationProps {
  navItems?: NavItem[];

  isAuthenticated?: boolean;
  onLogin?: LoginHandler;
  onLogout?: LogoutHandler;
}

const defaultHandler = () => {};

export const Navigation: React.FC<NavigationProps> = ({
  navItems = [],
  isAuthenticated = false,
  onLogin = defaultHandler,
  onLogout = defaultHandler,
}) => {
  const items = navItems
    .filter((item) => isAuthenticated || !item.secured)
    .map((item) => (
      <li key={item.path}>
        <NavLink exact to={item.path} activeClassName="active">
          {item.label}
        </NavLink>
      </li>
    ));

  return (
    <nav className="navigation">
      <section className="menu">
        <ul>{items}</ul>
      </section>

      <section className="auth">
        <LoginButton
          isAuthenticated={isAuthenticated}
          onLogin={onLogin}
          onLogout={onLogout}
        />
      </section>
    </nav>
  );
};
